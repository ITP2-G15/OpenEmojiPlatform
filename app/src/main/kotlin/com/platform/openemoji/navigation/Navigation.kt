package com.platform.openemoji.navigation

import GameScreen
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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.platform.openemoji.Application
import com.platform.openemoji.R
import com.platform.openemoji.RepositoryStore
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueViewModel
import com.platform.openemoji.events.EventViewModel
import com.platform.openemoji.news.NewsViewModel
import com.platform.openemoji.screens.EmojiDetailScreen
import com.platform.openemoji.screens.EventListScreen
import com.platform.openemoji.screens.HomeScreen
import com.platform.openemoji.screens.NewsListScreen
import com.platform.openemoji.screens.SearchScreen

@Composable
fun Navigation(
    // Allows tests to use custom repositories
    repositories: RepositoryStore =
        LocalContext.current.applicationContext as Application,
) {
    val overview = stringResource(R.string.overview)
    val emojiCatalogueViewModel =
        viewModel(key = "emojiCatalogue") {
            EmojiCatalogueViewModel(repositories.emojiRepository, overview)
        }

    val newsViewModel =
        viewModel(key = "news") {
            NewsViewModel(repositories.newsRepository)
        }

    val eventViewModel =
        viewModel(key = "event") {
            EventViewModel(repositories.eventsRepository)
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
                                repositories.emojiRepository.getEmoji(name)
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
                    HomeScreen(
                        emojiCatalogueViewModel,
                        eventViewModel,
                        newsViewModel,
                        navController,
                    )
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
                    EventListScreen(eventViewModel, navController)
                }
                composable(route = Screen.GameScreen.route) {
                    GameScreen(navController)
                }
            }
        }
    }
}
