package com.platform.openemoji.screens

import android.content.res.Resources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.platform.openemoji.emoji.EmojiMockData
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueComponent
import com.platform.openemoji.emoji.category.CategoryScrollCarousel
import com.platform.openemoji.emoji.category.route

@Composable
fun SearchScreen(resources: Resources) {
    val emojiCatalogue = EmojiCatalogue(EmojiMockData.getFrom(resources))

    Column(modifier = Modifier.padding(16.dp)) {
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
