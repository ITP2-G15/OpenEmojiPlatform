package com.platform.openemoji.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

// @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController? = null) {
    Text(
        text =
            if (navController == null) {
                "Navigation Controller not available"
            } else {
                "This is the Game Screen"
            },
        modifier = Modifier.testTag("gameScreen"),
    )
}

@Preview(showBackground = true)
@Composable
fun GamesScreenPreview() {
    GameScreen()
}
