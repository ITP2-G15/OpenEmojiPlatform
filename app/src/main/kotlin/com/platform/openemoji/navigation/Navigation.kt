package com.platform.openemoji.navigation

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
import com.platform.openemoji.navigation.NavigationItem
import com.platform.openemoji.screens.EmojiDetailScreen
import com.platform.openemoji.screens.SearchScreen

@Composable
fun Navigation() {
    val emojiCatalogue = EmojiCatalogue.get()
    val allEmojis = emojiCatalogue.byCategory

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationItem.SearchScreen.route,
    ) {
        /**
         * Routing for different screens
         */
        composable(route = NavigationItem.SearchScreen.route,) {
            SearchScreen()
        }
    }
}
