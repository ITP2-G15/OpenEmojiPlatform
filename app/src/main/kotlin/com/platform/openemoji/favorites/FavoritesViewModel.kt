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
            getFavorites()
        }
    }

    fun setCurrentFavoriteName(name: String) {
        val favorite = _currentFavorite.value ?: Favorite("", "")
        _currentFavorite.value = favorite.copy(name = name)
    }

    fun appendToCurrentFavoriteEmojiSequence(emojiCode: String) {
        val favorite = _currentFavorite.value ?: Favorite("", "")
        _currentFavorite.value =
            favorite.copy(
                emojiSequence = favorite.emojiSequence + emojiCode,
            )
    }

    fun clearCurrentFavorite() {
        _currentFavorite.value = null
    }
}
