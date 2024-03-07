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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.platform.openemoji.R
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueUi
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import com.platform.openemoji.emoji.category.CategoryScrollCarousel
import com.platform.openemoji.emoji.category.route
import com.platform.openemoji.search.SearchField

@Composable
fun SearchScreen() {
    val emojiCatalogue = EmojiCatalogue.get()
    // Creates a state for the search text
    val query = remember { mutableStateOf("") }

    Column {
        SearchField(query)

        val categoryNav = rememberNavController()

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
                EmojiGrid(emojis = filteredEmojis)
            }
        } else {
            // Remember the string resource here
            val overview = stringResource(R.string.overview)
            val lowerCaseOverview = overview.lowercase()
            CategoryScrollCarousel(
                categoryNav,
                listOf(overview) + emojiCatalogue.categories,
            )
            NavHost(
                navController = categoryNav,
                startDestination = "search/$lowerCaseOverview",
            ) {
                // Categories (All)
                composable(
                    "search/$lowerCaseOverview",
                ) {
                    EmojiCatalogueUi(
                        emojis = emojiCatalogue.byCategory,
                    )
                }
                // Subcategories (e.g. Activities > Sport)
                emojiCatalogue.categories.forEach { category ->
                    composable(
                        "search/${route(category)}",
                    ) {
                        emojiCatalogue.bySubCategory(category)?.let {
                            EmojiCatalogueUi(
                                emojis = it,
                            )
                        }
                    }
                }
            }
        }
    }
}
