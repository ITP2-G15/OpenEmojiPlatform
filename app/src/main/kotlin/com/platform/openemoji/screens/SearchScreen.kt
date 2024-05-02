package com.platform.openemoji.screens

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.platform.openemoji.LocalAnalytics
import com.platform.openemoji.R
import com.platform.openemoji.RepositoryStore
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueViewModel
import com.platform.openemoji.emoji.catalogue.SearchScreenEmojiCatalogue
import com.platform.openemoji.emoji.category.SearchScreenEmojiCategoryCarousel
import com.platform.openemoji.header.HeaderLogo
import com.platform.openemoji.search.SearchField
import com.platform.openemoji.search.SearchViewModel

@Composable
fun SearchScreen(
    repositories: RepositoryStore =
        LocalContext.current.applicationContext as Application,
    emojiCatalogueViewModel: EmojiCatalogueViewModel,
    navController: NavController,
) {
    val analytics = LocalAnalytics.current

    val searchViewModel: SearchViewModel =
        viewModel {
            SearchViewModel(repositories.emojiRepository)
        }

    val searchQuery by searchViewModel.searchQuery.collectAsState()
    val searchResults by searchViewModel.searchResults
        .collectAsState(emptyList())

    val searchResultsAreLoading by searchViewModel.searchResultsAreLoading
        .collectAsState()

    // Collect search data for analytics
    LaunchedEffect(analytics) {
        analytics?.let { searchViewModel.useSearchAnalytics(it) }
    }

    // Load category names and the initial overview emojis
    LaunchedEffect(LocalLifecycleOwner.current) {
        emojiCatalogueViewModel.loadCatalogue()
    }

    Column(
        modifier = Modifier.testTag("searchScreen"),
    ) {
        HeaderLogo()
        SearchField(searchQuery) { searchViewModel.search(it) }

        if (searchQuery.isEmpty()) {
            // When user is not searching for emojis
            SearchScreenEmojiCategoryCarousel(emojiCatalogueViewModel)
            SearchScreenEmojiCatalogue(
                emojiCatalogueViewModel,
                navController,
            )
        } else {
            // When user is searching for emojis
            val searchResultHeadline =
                if (searchResults.isEmpty() && !searchResultsAreLoading) {
                    "${stringResource(R.string.no_search_results)} $searchQuery"
                } else {
                    "${stringResource(R.string.search_result)} $searchQuery"
                }
            // The headline serves as the name for the single category in this catalogue
            // that contains the search results
            EmojiCatalogue(
                emojisByCategory = mapOf(searchResultHeadline to searchResults),
                navController,
                emojisOfCategoryAreLoading = { searchResultsAreLoading },
                modifier = Modifier.verticalScroll(rememberScrollState()),
            )
        }
    }
}
