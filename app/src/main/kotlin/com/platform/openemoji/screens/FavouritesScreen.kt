package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import com.platform.openemoji.navigation.BackButtonNavigation

@Composable
fun FavouritesScreen(navController: NavController) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .testTag("favouritesScreen"),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /**
         * Arrow back button, takes you back to previous location.
         */
        BackButtonNavigation(
            navController = navController,
            modifier = Modifier.testTag("favouritesBackButton"),
        )
    }
}
