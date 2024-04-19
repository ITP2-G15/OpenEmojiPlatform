package com.platform.openemoji.navigation

import GameScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.platform.openemoji.favorites.FavoritesViewModel
import com.platform.openemoji.news.NewsViewModel
import com.platform.openemoji.screens.EmojiDetailScreen
import com.platform.openemoji.screens.EventListScreen
import com.platform.openemoji.screens.FavoritesScreen
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

    val favoritesViewModel =
        viewModel(key = "favorites") {
            FavoritesViewModel(repositories.favoritesRepository)
        }

    val bottomBarViewModel: BottomBarViewModel = viewModel()

    val currentOrderValue by bottomBarViewModel.currentOrderValue.collectAsState(
        initial = 1,
    )
    val previousOrderValue by bottomBarViewModel.previousOrderValue.collectAsState(
        initial = null,
    )

    val navController = rememberNavController()
    val startDestination = Screen.HomeScreen.route
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController, startDestination) },
    ) { paddingValues ->
        Surface(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            NavHost(
                navController = navController,
                startDestination = startDestination,
                enterTransition =
                    selectEnterTransition(
                        currentOrderValue,
                        previousOrderValue,
                    ),
                exitTransition =
                    selectExitTransition(
                        currentOrderValue,
                        previousOrderValue,
                    ),
            ) {
                /**
                 * Routing for SearchScreen
                 */
                composable(
                    route = Screen.SearchScreen.route,
                    popEnterTransition =
                        slideEnterTransition(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                        ),
                ) {
                    SearchScreen(emojiCatalogueViewModel, navController)
                }

                /**
                 * Routing for FavoritesScreen
                 */
                composable(
                    route = Screen.FavoritesScreen.route,
                    popEnterTransition =
                        slideEnterTransition(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                        ),
                ) {
                    FavoritesScreen(favoritesViewModel)
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
                    enterTransition = slideEnterTransition(),
                    popExitTransition = slideExitTransition(),
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
                            favoritesViewModel = favoritesViewModel,
                            emoji = it,
                            navController = navController,
                        )
                    }
                }

                /**
                 * Navigates to HomeScreen.
                 */
                composable(
                    route = Screen.HomeScreen.route,
                    popEnterTransition =
                        slideEnterTransition(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                        ),
                ) {
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
                composable(
                    route = Screen.NewsListScreen.route,
                    enterTransition = slideEnterTransition(),
                    popExitTransition = slideExitTransition(),
                ) {
                    NewsListScreen(newsViewModel, navController)
                }

                /**
                 * Navigates to EventListScreen.
                 */
                composable(
                    route = Screen.EventListScreen.route,
                    enterTransition = slideEnterTransition(),
                    popExitTransition = slideExitTransition(),
                ) {
                    EventListScreen(eventViewModel, navController)
                }
                composable(
                    route = Screen.GameScreen.route,
                    popExitTransition = slideExitTransition(),
                ) {
                    GameScreen(navController)
                }
            }
        }
    }
}
