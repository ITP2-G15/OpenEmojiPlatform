package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.platform.openemoji.R
import com.platform.openemoji.favorites.FavoritesViewModel
import com.platform.openemoji.favorites.card.FavoriteCard
import com.platform.openemoji.header.HeaderLogo

@Composable
fun FavoritesScreen(favoritesViewModel: FavoritesViewModel) {
    val favoritesState = favoritesViewModel.favorites.collectAsState()
    val favorites = favoritesState.value ?: listOf()

    Column {
        HeaderLogo()
        if (favorites.isEmpty()) {
            Text(
                stringResource(R.string.no_favorites),
                style = MaterialTheme.typography.titleLarge,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                        .padding(16.dp),
                textAlign = TextAlign.Center,
            )
        } else {
            LazyColumn(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag("favoritesScreen"),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(favorites) { favorite ->
                    FavoriteCard(favoritesViewModel, favorite)
                }
                item {
                    Spacer(modifier = Modifier.height(96.dp))
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        FloatingActionButton(
            onClick = {},
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
            content = {
                Icon(
                    Icons.Default.Create,
                    contentDescription =
                        stringResource(R.string.start_sequence_icon_description),
                )
            },
        )
    }
}
