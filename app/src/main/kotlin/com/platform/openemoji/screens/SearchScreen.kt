package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueUi
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import com.platform.openemoji.emoji.category.CategoryScrollCarousel
import com.platform.openemoji.emoji.category.route

@Composable
fun SearchScreen() {
    val emojiCatalogue = EmojiCatalogue.get()
    // Creates a state for the search text
    val searchText = remember { mutableStateOf("") }

    Column {
        // Displays a TextField at the top of the screen
        TextField(
            value = searchText.value,
            onValueChange = { newText ->
                searchText.value = newText
            },
            label = {
                Text("Search for emoji")
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            shape = RoundedCornerShape(12.dp),
        )

        if (searchText.value.isNotEmpty()) {
            Text(
                "Search results for: ${searchText.value}",
                modifier = Modifier.padding(bottom = 10.dp),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
            )
            // filters emoji based on title
            val filteredEmojis =
                emojiCatalogue.byCategory
                    .mapValues {
                        it.value.filter { emoji ->
                            emoji.title.contains(searchText.value, ignoreCase = true)
                        }
                    }
                    .values
                    .flatten()
            EmojiGrid(emojis = filteredEmojis)
        } else {
            val categoryNav = rememberNavController()
            CategoryScrollCarousel(
                categoryNav,
                listOf("All") + emojiCatalogue.categories,
            )
            NavHost(
                navController = categoryNav,
                startDestination = "search/all",
            ) {
                // Categories (All)
                composable(
                    "search/all",
                ) {
                    EmojiCatalogueUi(
                        emojis = emojiCatalogue.byCategory,
                        maxEmojisPerGrid = 15,
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
