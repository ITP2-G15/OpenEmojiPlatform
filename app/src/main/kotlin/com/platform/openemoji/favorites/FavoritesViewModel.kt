package com.platform.openemoji.favorites

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository,
) : ViewModel() {
    private val _favorites = MutableStateFlow<List<News>?>(null)
    val favorites = _favorites.asStateFlow()

    val currentFavorite: Favorite? = null
}
