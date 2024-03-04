package com.platform.openemoji.navigation

import androidx.compose.runtime.Composable
import


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.category.route
import com.platform.openemoji.emoji.category.CategoryScrollCarousel
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueUi
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.navigation.Screen
import com.platform.openemoji.screens.EmojiDetailScreen
import com.platform.openemoji.screens.SearchScreen

@Composable
fun Navigation() {
    val emojiCatalogue = EmojiCatalogue.get()

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SearchScreen.route,
    ) {
        /**
         * Routing for different screens
         */
        composable(route = Screen.SearchScreen.route,) {
            SearchScreen()
        }
        composable(
            route = Screen.EmojiDetailScreen.route
        ) {backStackEntry ->
            val emojiName = backStackEntry.arguments?.get("emojiName", Emoji)
            emojiCatalogue.
            if (emojiName != null) {
                //val emoji
                //if (emoji != null) {
                //    EmojiDetailScreen(navController, emoji)
                //}
            }
        }

        /**
         * Routing for categories within SearchScreen.
         */
        composable(
            Screen.AllCategories.route,
        ) {
            EmojiCatalogueUi(
                emojis = emojiCatalogue.byCategory,
                maxEmojisPerGrid = 15,
                filterText = ""            )
        }
        // Subcategories (e.g. Activities > Sport)
        emojiCatalogue.categories.forEach { category ->
            composable(
                "search/${route(category)}",
            ) {
                emojiCatalogue.bySubCategory(category)?.let {
                    EmojiCatalogueUi(
                        emojis = it,
                        maxEmojisPerGrid = 15,
                        filterText = ""
                    )
                }
            }
        }
    }
}
