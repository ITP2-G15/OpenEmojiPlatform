package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.platform.openemoji.emoji.category.CategoryScrollCarousel
import com.platform.openemoji.emoji.category.route

@Composable
fun SearchScreen(resources: Resources) {
    val emojiCatalogue = EmojiCatalogue(EmojiMockData.getFrom(resources))

    Column {
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
