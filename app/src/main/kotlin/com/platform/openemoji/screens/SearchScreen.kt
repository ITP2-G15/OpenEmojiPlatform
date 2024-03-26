package com.platform.openemoji.screens

import HeaderLogo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueViewModel
import com.platform.openemoji.emoji.catalogue.SearchScreenEmojiCatalogue
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import com.platform.openemoji.emoji.category.EmojiCategoryViewModel
import com.platform.openemoji.emoji.category.SearchScreenEmojiCategoryCarousel
import com.platform.openemoji.search.SearchField

@Composable
fun SearchScreen(
    emojiCatalogueViewModel: EmojiCatalogueViewModel,
    navController: NavController,
) {
    val application = LocalContext.current.applicationContext as Application

    val emojiCategoryViewModel: EmojiCategoryViewModel =
        viewModel(key = "emojiCategory") {
            EmojiCategoryViewModel(application.emojiRepository)
        }
    val overview = stringResource(R.string.overview)
    LaunchedEffect(LocalLifecycleOwner.current) {
        emojiCategoryViewModel.selectCategory(overview)
    }

    val searchQuery = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.testTag("searchScreen"),
    ) {
        HeaderLogo()
        SearchField(searchQuery.value) { searchQuery.value = it }

        if (searchQuery.value.isNotEmpty()) {
            Column(
                modifier =
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "${stringResource(
                        R.string.search_result,
                    )} ${searchQuery.value}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                // val filteredEmojis by viewModel.emojisBySearch.observeAsState(emptyList())
                EmojiGrid(emojis = emptyList(), navController = navController)
            }
        } else {
            SearchScreenEmojiCategoryCarousel(emojiCategoryViewModel)
            SearchScreenEmojiCatalogue(
                emojiCatalogueViewModel,
                emojiCategoryViewModel,
                navController,
            )
        }
    }
}
