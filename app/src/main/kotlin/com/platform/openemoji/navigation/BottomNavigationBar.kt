package com.platform.openemoji.navigation

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.platform.openemoji.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val selectedItem = navController.currentBackStackEntry?.destination?.route
    NavigationBar {
        NavigationBarItem(
            selected = selectedItem == stringResource(R.string.home),
            label = {
                Text(stringResource(R.string.home))
            },
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = stringResource(R.string.home_icon_description),
                    modifier =
                        Modifier.clickable {
                            navController.navigate(
                                NavigationItem.HomeScreen.route,
                            )
                        },
                )
            },
            onClick = {},
        )
        NavigationBarItem(
            selected = selectedItem == stringResource(R.string.search),
            label = {
                Text(stringResource(R.string.search))
            },
            icon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search_icon_description),
                    modifier =
                        Modifier.clickable {
                            navController.navigate(
                                NavigationItem.SearchScreen.route,
                            )
                        },
                )
            },
            onClick = {},
        )
    }
}
