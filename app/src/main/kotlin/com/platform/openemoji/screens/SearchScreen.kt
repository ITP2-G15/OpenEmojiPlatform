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
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueUi
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import com.platform.openemoji.emoji.category.CategoryScrollCarousel

@Composable
fun SearchScreen(navController: NavController) {
    val emojiCatalogue = EmojiCatalogue.get()
    // Creates a state for the search text
    val query = remember { mutableStateOf("") }
    val categorizedEmojis = remember { mutableStateOf(emojiCatalogue.byCategory) }

    Column {
        // Displays a TextField at the top of the screen
        TextField(
            value = query.value,
            onValueChange = { newText ->
                query.value = newText
            },
            label = {
                Text(stringResource(R.string.search_for_emoji))
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            shape = RoundedCornerShape(12.dp),
        )

        if (query.value.isNotEmpty()) {
            Column(
                modifier =
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(start = 16.dp, end = 16.dp),
            ) {
                Text(
                    text = "Search results for: ${query.value}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                val filteredEmojis = emojiCatalogue.search(query.value)
                EmojiGrid(emojis = filteredEmojis, navController = navController)
            }
        } else {
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
