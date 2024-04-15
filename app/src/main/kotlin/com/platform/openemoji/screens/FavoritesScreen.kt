package com.platform.openemoji.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.platform.openemoji.favorites.FavoriteCard
import com.platform.openemoji.favorites.FavoritesViewModel
import com.platform.openemoji.header.HeaderLogo

@Composable
fun FavoritesScreen(favoritesViewModel: FavoritesViewModel) {
    val favoritesState = favoritesViewModel.favorites.collectAsState()
    val favorites = favoritesState.value ?: listOf()

    LazyColumn(
        modifier =
            Modifier
                .fillMaxWidth()
                .testTag("favouritesScreen"),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item { HeaderLogo() }

        items(favorites) { favorite ->
            FavoriteCard(favoritesViewModel, favorite)
        }
    }
}
