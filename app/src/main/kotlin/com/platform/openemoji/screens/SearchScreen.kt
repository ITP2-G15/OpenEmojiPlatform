package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueUi
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import com.platform.openemoji.emoji.category.CategoryScrollCarousel
import com.platform.openemoji.emoji.category.route
import com.platform.openemoji.search.SearchField

@Composable
fun SearchScreen(navController: NavController) {
    val emojiCatalogue = EmojiCatalogue.get()
    // Creates a state for the search text
    val query = remember { mutableStateOf("") }
    val categorizedEmojis = remember { mutableStateOf(emojiCatalogue.byCategory) }

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
            // Remember the string resource here
            val overview = stringResource(R.string.overview)
            val lowerCaseOverview = overview.lowercase()
            CategoryScrollCarousel(
                listOf(stringResource(R.string.all)) + emojiCatalogue.categories,
            ) { newCategory ->
                categorizedEmojis.value =
                    if (newCategory == "All") {
                        emojiCatalogue.byCategory
                    } else {
                        emojiCatalogue.bySubCategory(newCategory) ?: emptyMap()
                    }
            }
            EmojiCatalogueUi(
                emojis = categorizedEmojis.value,
                maxEmojisPerGrid = 15,
                navController = navController,
            )
        }
    }
}
