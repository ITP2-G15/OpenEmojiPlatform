package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.platform.openemoji.emoji.category.Category
import com.platform.openemoji.emoji.category.CategoryScrollCarousel

@Composable
fun CategoryGrid(name: String) {
    Text(name)
}

@Composable
fun SearchScreen() {
    val categoryList =
        listOf(
            Category("All", "all"),
            Category("Smileys & Emotion", "smileys"),
            Category("People & Body", "people"),
            Category("Animals & Nature", "animals"),
            Category("Food & Drink", "food"),
            Category("Travel & Places", "travel"),
            Category("Activities", "activities"),
            Category("Objects", "object"),
            Category("Symbols", "symbols"),
            Category("Flags", "flags"),
        )

    Column {
        val categoryNav = rememberNavController()
        CategoryScrollCarousel(categoryNav, emojiCatalogue.categories)
        NavHost(
            navController = categoryNav,
            startDestination = "search",
        ) {
            // Categories
            composable(
                "search",
            ) {
                EmojiCatalogueComponent(
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
                        EmojiCatalogueComponent(
                            emojis = it,
                            maxEmojisPerGrid = 15,
                        )
                    }
                }
            }
        }
    }
}
