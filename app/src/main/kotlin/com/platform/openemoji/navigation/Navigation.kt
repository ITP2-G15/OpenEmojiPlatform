package com.platform.openemoji.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.platform.openemoji.R
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.screens.EmojiDetailScreen
import com.platform.openemoji.screens.EventListScreen
import com.platform.openemoji.screens.HomeScreen
import com.platform.openemoji.screens.NewsListScreen
import com.platform.openemoji.screens.SearchScreen

@Composable
fun Navigation() {
    val emojiCatalogue = EmojiCatalogue.get()

    val navController = rememberNavController()
    val startDestination = Screen.HomeScreen.route
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController, startDestination) },
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
            color = MaterialTheme.colorScheme.background,
        ) {
            NavHost(
                navController = navController,
                startDestination = startDestination,
            ) {
                /**
                 * Routing for SearchScreen
                 */
                composable(
                    route = Screen.SearchScreen.route,
                ) {
                    SearchScreen(navController = navController)
                }

                /**
                 * Routing for EmojiDetailScreen
                 */
                composable(
                    route = Screen.EmojiDetailScreen.route + "/{emojiTitle}",
                    arguments =
                        listOf(
                            navArgument("emojiTitle") {
                                type = NavType.StringType
                                nullable = true
                            },
                        ),
                ) { backStackEntry ->
                    val emojiTitle = backStackEntry.arguments?.getString("emojiTitle")
                    val emoji = emojiTitle?.let { title -> emojiCatalogue.emoji(title) }
                    if (emoji != null) {
                        EmojiDetailScreen(emoji = emoji, navController = navController)
                    } else {
                        throw IllegalArgumentException(
                            stringResource(R.string.emoji_not_found),
                        )
                    }
                }

                /**
                 * Navigates to HomeScreen.
                 */
                composable(route = Screen.HomeScreen.route) {
                    HomeScreen(navController = navController)
                }
                /**
                 * Navigates to NewsListScreen.
                 */
                composable(route = Screen.NewsListScreen.route) {
                    NewsListScreen(navController = navController)
                }
                /**
                 * Navigates to EventListScreen.
                 */
                composable(route = Screen.EventListScreen.route) {
                    EventListScreen(navController = navController)
                }
            }
        }
    }
}
