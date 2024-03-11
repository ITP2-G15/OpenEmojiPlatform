package com.platform.openemoji.screens

import HeaderLogo
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.testTag("homeScreen"),
    ) {
        HeaderLogo()
        Text(text = "Home")
    }
}
