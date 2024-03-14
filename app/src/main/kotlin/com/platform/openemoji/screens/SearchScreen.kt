package com.platform.openemoji.screens

import HeaderLogo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.platform.openemoji.EmojiApplication
import com.platform.openemoji.EmojiViewModel
import com.platform.openemoji.R
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import com.platform.openemoji.emoji.category.CategoryScrollCarousel
import com.platform.openemoji.search.SearchField

@Composable
fun SearchScreen(navController: NavController) {
    val application = LocalContext.current.applicationContext as EmojiApplication
    val viewModel: EmojiViewModel =
        viewModel("emojiViewModel") {
            EmojiViewModel(application.emojiRepository)
        }
    val overview = stringResource(R.string.overview)
    val categories by viewModel.categories.observeAsState(emptyList())
    val overviewEmojisByCategory by viewModel.overviewEmojisByCategory.observeAsState(
        emptyMap(),
    )
    val allEmojisByCategory by viewModel.allEmojisByCategory.observeAsState(emptyMap())
    val selectedCategory = rememberSaveable { mutableStateOf(overview) }
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
                val filteredEmojis by viewModel.emojisBySearch.observeAsState(emptyList())
                EmojiGrid(emojis = filteredEmojis, navController = navController)
            }
        } else {
            CategoryScrollCarousel(
                selectedCategory.value,
                listOf(overview) + categories,
            ) { selectedCategory.value = it }
            if (selectedCategory.value == overview) {
                EmojiCatalogue(emojisByCategory = overviewEmojisByCategory, navController)
            }
            // else {
            //     EmojiGrid(emojis = , navController)
            // }
        }
    }
}
