package com.platform.openemoji.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.platform.openemoji.Application
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueViewModel
import com.platform.openemoji.news.NewsViewModel
import com.platform.openemoji.screens.EmojiDetailScreen
import com.platform.openemoji.screens.EventListScreen
import com.platform.openemoji.screens.HomeScreen
import com.platform.openemoji.screens.NewsListScreen
import com.platform.openemoji.screens.SearchScreen

@Composable
fun Navigation() {
    val application = LocalContext.current.applicationContext as Application

    val emojiCatalogueViewModel =
        viewModel(key = "emojiCatalogue") {
            EmojiCatalogueViewModel(application.emojiRepository)
        }

    val newsViewModel =
        viewModel(key = "news") {
            NewsViewModel(application.newsRepository)
        }

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
                    SearchScreen(emojiCatalogueViewModel, navController)
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
                    val emoji = remember { mutableStateOf<Emoji?>(null) }
                    LaunchedEffect(LocalLifecycleOwner.current) {
                        emoji.value =
                            emojiName?.let { name ->
                                application.emojiRepository.getEmoji(name)
                            }
                    }

                    emoji.value?.let {
                        EmojiDetailScreen(
                            emoji = it,
                            navController = navController,
                        )
                    }
                }

                /**
                 * Navigates to HomeScreen.
                 */
                composable(route = Screen.HomeScreen.route) {
                    HomeScreen(emojiCatalogueViewModel, newsViewModel, navController)
                }
                /**
                 * Navigates to NewsListScreen.
                 */
                composable(route = Screen.NewsListScreen.route) {
                    NewsListScreen(newsViewModel, navController)
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
