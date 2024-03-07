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
import com.platform.openemoji.screens.HomeScreen
import com.platform.openemoji.screens.SearchScreen

@Composable
fun Navigation() {
    val emojiCatalogue = EmojiCatalogue.get()

    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) },
    ) {paddingValues->
        Surface(
            modifier = Modifier.padding(paddingValues),
            color = MaterialTheme.colorScheme.background,
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.SearchScreen.route,
            ) {
                /**
                 * Routing for SearchScreen
                 */
                composable(
                    route = NavigationItem.SearchScreen.route,
                ) {
                    SearchScreen(navController = navController)
                }

                /**
                 * Routing for EmojiDetailScreen
                 */
                composable(
                    route = NavigationItem.EmojiDetailScreen.route + "/{emojiTitle}",
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
                 * If more screens are necessary, add them here and also add them to
                 * navigationItem
                 */
                composable(route = NavigationItem.HomeScreen.route) {
                    HomeScreen(navController = navController)
                }
            }
        }
    }
}
