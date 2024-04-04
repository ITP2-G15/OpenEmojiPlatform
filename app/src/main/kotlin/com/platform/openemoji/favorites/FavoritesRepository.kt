package com.platform.openemoji.favorites

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FavoritesRepository(private val context: Context) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "favorites",
    )

    val favoritesPreferencesKey = stringPreferencesKey("favorites")

    fun getFavorites(): Flow<List<Favorite>> {
        return context.dataStore.data
            .map { preferences ->
                val favoritesString = preferences[favoritesPreferencesKey] ?: ""
                stringToFavorites(favoritesString)
            }
    }

    suspend fun addFavorite(favorite: Favorite) {
        context.dataStore.edit { settings ->
            val currentFavoritesString = settings[favoritesPreferencesKey] ?: ""
            settings[favoritesPreferencesKey] =
                favoritesToString(
                    stringToFavorites(currentFavoritesString) + favorite,
                )
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
