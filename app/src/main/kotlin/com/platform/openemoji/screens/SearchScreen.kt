package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.platform.openemoji.Application
import com.platform.openemoji.R
import com.platform.openemoji.RepositoryStore
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueViewModel
import com.platform.openemoji.emoji.catalogue.SearchScreenEmojiCatalogue
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import com.platform.openemoji.emoji.category.SearchScreenEmojiCategoryCarousel
import com.platform.openemoji.header.HeaderLogo
import com.platform.openemoji.search.EmojiSearchViewModel
import com.platform.openemoji.search.SearchField

@Composable
fun SearchScreen(
    emojiCatalogueViewModel: EmojiCatalogueViewModel,
    navController: NavController,
    // Allows tests to use custom repositories
    repositories: RepositoryStore =
        LocalContext.current.applicationContext as Application,
) {
    val emojiSearchViewModel: EmojiSearchViewModel =
        viewModel(key = "emojiSearch") {
            EmojiSearchViewModel(repositories.emojiRepository)
        }
    val searchQuery by emojiSearchViewModel.searchQuery.collectAsState()
    val searchResults by emojiSearchViewModel.searchResults
        .collectAsState(emptyList())
    val searchResultsAreLoading by emojiSearchViewModel.searchResultsAreLoading
        .collectAsState()

    // Load category names and the initial overview emojis.
    LaunchedEffect(LocalLifecycleOwner.current) {
        emojiCatalogueViewModel.loadCatalogue()
    }

    Column(
        modifier = Modifier.testTag("searchScreen"),
    ) {
        HeaderLogo()
        SearchField(searchQuery) { emojiSearchViewModel.search(it) }

        if (searchQuery.isNotEmpty()) {
            Column(
                modifier =
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "${stringResource(
                        R.string.search_result,
                    )} $searchQuery",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                if (!searchResultsAreLoading) {
                    EmojiGrid(emojis = searchResults, navController = navController)
                }
            }
        } else {
            SearchScreenEmojiCategoryCarousel(emojiCatalogueViewModel)
            SearchScreenEmojiCatalogue(
                emojiCatalogueViewModel,
                navController,
            )
        }
    }
}
