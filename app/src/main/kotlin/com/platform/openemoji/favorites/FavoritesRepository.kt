package com.platform.openemoji.favorites

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface FavoritesRepository {
    fun getFavorites(): Flow<List<Favorite>> = flowOf(emptyList())

    suspend fun addFavorite(favorite: Favorite) = Unit

    suspend fun deleteFavorite(favorite: Favorite) = Unit
}

class FavoritesDataRepository(
    private val context: Context?,
    private val testFavorites: MutableList<Favorite>? = null,
) : FavoritesRepository {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "favorites",
    )

    val favoritesPreferencesKey = stringPreferencesKey("favorites")

    override fun getFavorites(): Flow<List<Favorite>> {
        return if (context != null) {
            context.dataStore.data
                .map { preferences ->
                    val favoritesString = preferences[favoritesPreferencesKey] ?: ""
                    stringToFavorites(favoritesString)
                }
        } else {
            flowOf(testFavorites ?: emptyList())
        }
    }

    override suspend fun addFavorite(favorite: Favorite) {
        if (context != null) {
            context.dataStore.edit { settings ->
                val currentFavoritesString = settings[favoritesPreferencesKey] ?: ""
                settings[favoritesPreferencesKey] =
                    favoritesToString(
                        stringToFavorites(currentFavoritesString) + favorite,
                    )
            }
        } else {
            testFavorites?.add(favorite)
        }
    }

    override suspend fun deleteFavorite(favorite: Favorite) {
        if (context != null) {
            context.dataStore.edit { settings ->
                val currentFavoritesString = settings[favoritesPreferencesKey] ?: ""
                settings[favoritesPreferencesKey] =
                    favoritesToString(
                        stringToFavorites(currentFavoritesString)
                            .filterNot { it == favorite },
                    )
            }
        } else {
            testFavorites?.remove(favorite)
        }
    }

    private fun stringToFavorites(favoritesString: String): List<Favorite> {
        return if (favoritesString.isBlank()) {
            emptyList()
        } else {
            Json.decodeFromString<List<Favorite>>(favoritesString)
        }
    }

    private fun favoritesToString(favorites: List<Favorite>): String {
        return Json.encodeToString(favorites)
    }
}
