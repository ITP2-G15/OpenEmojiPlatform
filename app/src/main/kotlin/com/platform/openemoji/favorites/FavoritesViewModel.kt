package com.platform.openemoji.favorites

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository,
) : ViewModel() {
    private val _favorites = MutableStateFlow<List<Favorite>?>(null)
    val favorites = _favorites.asStateFlow()

    private val _currentFavorite = MutableStateFlow<Favorite?>(null)
    val currentFavorite = _currentFavorite.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            getFavorites()
        }
    }

    private suspend fun getFavorites() {
        favoritesRepository.getFavorites().collect {
            _favorites.value = it
        }
    }

    suspend fun addCurrentFavoriteToFavorites() {
        _currentFavorite.value?.let { favorite ->
            favoritesRepository.addFavorite(favorite)
            clearCurrentFavorite()
            getFavorites()
        }
    }

    suspend fun deleteFavorite(favorite: Favorite) {
        favoritesRepository.deleteFavorite(favorite)
        getFavorites()
    }

    fun setCurrentFavoriteName(name: String) {
        val favorite = _currentFavorite.value ?: Favorite("", arrayOf())
        _currentFavorite.value = favorite.copy(name = name)
    }

    fun appendToCurrentFavoriteEmojiCodes(emojiCode: String) {
        val favorite = _currentFavorite.value ?: Favorite("", arrayOf())
        _currentFavorite.value =
            favorite.copy(
                emojiCodes = favorite.emojiCodes + emojiCode,
            )
    }

    fun removeLastEmojiCodeFromCurrentFavorite() {
        val favorite = _currentFavorite.value ?: Favorite("", arrayOf())
        _currentFavorite.value =
            favorite.copy(
                emojiCodes = favorite.emojiCodes.dropLast(1).toTypedArray(),
            )
        if (favorite.emojiCodes.size == 1) {
            clearCurrentFavorite()
        }
    }

    fun clearCurrentFavorite() {
        _currentFavorite.value = null
    }
}
