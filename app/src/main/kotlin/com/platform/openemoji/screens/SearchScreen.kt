package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueUi
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import com.platform.openemoji.emoji.category.CategoryScrollCarousel
import com.platform.openemoji.header.HeaderLogo
import com.platform.openemoji.search.SearchField

@Composable
fun SearchScreen(navController: NavController) {
    val emojiCatalogue = EmojiCatalogue.get()
    val overview = stringResource(R.string.overview)
    val searchQuery = rememberSaveable { mutableStateOf("") }
    val selectedCategory = rememberSaveable { mutableStateOf(overview) }

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
                val filteredEmojis = emojiCatalogue.search(searchQuery.value)
                EmojiGrid(emojis = filteredEmojis, navController = navController)
            }
        } else {
            CategoryScrollCarousel(
                selectedCategory.value,
                listOf(overview) + emojiCatalogue.categories,
            ) { selectedCategory.value = it }
            EmojiCatalogueUi(
                emojis =
                    if (selectedCategory.value == overview) {
                        emojiCatalogue.byCategory
                    } else {
                        emojiCatalogue.bySubCategory(selectedCategory.value) ?: emptyMap()
                    },
                maxEmojisPerGrid =
                    if (selectedCategory.value == overview) {
                        15
                    } else {
                        null
                    },
                navController = navController,
            )
        }
    }
}
