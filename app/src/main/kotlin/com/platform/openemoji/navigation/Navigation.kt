package com.platform.openemoji.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.platform.openemoji.screens.EmojiDetailScreen
import com.platform.openemoji.screens.HomeScreen
import com.platform.openemoji.screens.SearchScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val startDestination = Screen.SearchScreen.route
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
                    route = Screen.EmojiDetailScreen.route + "/{emojiName}",
                    arguments =
                        listOf(
                            navArgument("emojiName") {
                                type = NavType.StringType
                                nullable = true
                            },
                        ),
                ) { backStackEntry ->
                    val emojiName = backStackEntry.arguments?.getString("emojiName")
                    /*
                    val emoji = emojiName?.let { name -> emojiCatalogue.emoji(name) }
                    if (emoji != null) {
                        EmojiDetailScreen(emoji = emoji, navController = navController)
                    } else {
                        throw IllegalArgumentException(
                            stringResource(R.string.emoji_not_found),
                        )
                    }
                     */
                }

                /**
                 * If more screens are necessary, add them here and also add them to
                 * navigationItem
                 */
                composable(route = Screen.HomeScreen.route) {
                    HomeScreen(navController = navController)
                }
            }
        }
    }
}
