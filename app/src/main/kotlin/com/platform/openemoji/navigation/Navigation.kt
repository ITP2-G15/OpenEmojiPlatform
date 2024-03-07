package com.platform.openemoji.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.screens.EmojiDetailScreen
import com.platform.openemoji.screens.SearchScreen

@Composable
fun Navigation() {
    val emojiCatalogue = EmojiCatalogue.get()

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationItem.SearchScreen.route,
    ) {
        /**
         * Nested routing for SearchScreen
         */
        navigation(
            startDestination = NavigationItem.SearchScreen.AllCategories.route,
            route = NavigationItem.SearchScreen.route
        ) {
            composable(
                route = NavigationItem.SearchScreen.AllCategories.route,
            ) {
                SearchScreen(
                    navController = navController,
                    emojis = emojiCatalogue.byCategory,
                    maxEmojisPerGrid = 15,
                )
            }
            emojiCatalogue.categories.forEach { category ->
                composable(
                    route = NavigationItem.SearchScreen.Categories.routeTo(category),
                ) {
                    emojiCatalogue.bySubCategory(category)?.let {
                        SearchScreen(
                            navController = navController,
                            emojis = it,
                            maxEmojisPerGrid = 15,
                        )
                    }
                }
            }
        }

        /**
         * Routing for EmojiDetailScreen
         */
        composable(
            route = NavigationItem.EmojiDetailScreen.route + "/{emojiTitle}",
            arguments = listOf(navArgument("emojiTitle") {
                type = NavType.StringType
                nullable = true
            })
        ) { backStackEntry ->
            val emojiTitle = backStackEntry.arguments?.getString("emojiTitle")
            val emoji = emojiCatalogue.getEmojiByTitle(emojiTitle)
            if (emoji != null) {
                EmojiDetailScreen(emoji = emoji, navController = navController)
            } else {
                throw IllegalArgumentException("Emoji not found")
            }
        }

        /**
         * If more screens are necessary, add them under here and also add them to
         * navigationItem
         */
    }
}