package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueUi
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import com.platform.openemoji.emoji.category.CategoryScrollCarousel
import com.platform.openemoji.search.SearchField

@Composable
fun SearchScreen(navController: NavController) {
    val emojiCatalogue = EmojiCatalogue.get()
    // Creates a state for the search text
    val query = remember { mutableStateOf("") }
    val categorizedEmojis = remember { mutableStateOf(emojiCatalogue.byCategory) }
    val maxEmojisPerGrid = remember { mutableStateOf<Int?>(null) }

    Column {
        SearchField(query)

        if (query.value.isNotEmpty()) {
            Column(
                modifier =
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "${stringResource(R.string.search_result)} ${query.value}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                val filteredEmojis = emojiCatalogue.search(query.value)
                EmojiGrid(emojis = filteredEmojis, navController = navController)
            }
        } else {
            val overview = stringResource(R.string.overview)
            CategoryScrollCarousel(
                listOf(overview) + emojiCatalogue.categories,
            ) { newCategory ->
                categorizedEmojis.value =
                    if (newCategory == overview) {
                        emojiCatalogue.byCategory
                    } else {
                        emojiCatalogue.bySubCategory(newCategory) ?: emptyMap()
                    }
                // Update max size per grid only when it is set to overview.
                if (newCategory == overview) {
                    maxEmojisPerGrid.value = 15
                } else {
                    maxEmojisPerGrid.value = null
                }
            }
            EmojiCatalogueUi(
                emojis = categorizedEmojis.value,
                maxEmojisPerGrid = maxEmojisPerGrid.value,
                navController = navController,
            )
        }
    }
}
