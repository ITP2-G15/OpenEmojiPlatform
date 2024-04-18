package com.platform.openemoji.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.platform.openemoji.R

@Composable
fun BottomNavigationBar(
    navController: NavController,
    startDestination: String,
) {
    NavigationBar {
        val selectedRootScreen = remember { mutableStateOf(startDestination) }
        NavigationBarItem(
            selected = selectedRootScreen.value == Screen.HomeScreen.route,
            label = {
                Text(stringResource(R.string.home))
            },
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription =
                        stringResource(
                            R.string.home_icon_description,
                        ),
                )
            },
            onClick = {
                selectedRootScreen.value = Screen.HomeScreen.route
                navController.navigate(Screen.HomeScreen.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            modifier = Modifier.testTag("bottomNavigationBarHome"),
        )
        NavigationBarItem(
            selected = selectedRootScreen.value == Screen.SearchScreen.route,
            label = {
                Text(stringResource(R.string.search))
            },
            icon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription =
                        stringResource(
                            R.string.search_icon_description,
                        ),
                )
            },
            onClick = {
                selectedRootScreen.value = Screen.SearchScreen.route
                navController.navigate(Screen.SearchScreen.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            modifier = Modifier.testTag("bottomNavigationBarSearch"),
        )
        NavigationBarItem(
            selected = selectedRootScreen.value == Screen.GameScreen.route,
            label = {
                Text(stringResource(R.string.games_icon_description))
            },
            icon = {
                Icon(
                    Icons.Default.Extension,
                    contentDescription =
                        stringResource(
                            R.string.games_icon_description,
                        ),
                )
            },
            onClick = {
                selectedRootScreen.value = Screen.GameScreen.route
                navController.navigate(Screen.GameScreen.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            modifier = Modifier.testTag("bottomNavigationBarGames"),
        )
        NavigationBarItem(
            selected = selectedRootScreen.value == Screen.FavoritesScreen.route,
            label = {
                Text(stringResource(R.string.favorite))
            },
            icon = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription =
                        stringResource(
                            R.string.favorite_icon_description,
                        ),
                )
            },
            onClick = {
                selectedRootScreen.value = Screen.FavoritesScreen.route
                navController.navigate(Screen.FavoritesScreen.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            modifier = Modifier.testTag("bottomNavigationBarFavourites"),
        )
    }
}
