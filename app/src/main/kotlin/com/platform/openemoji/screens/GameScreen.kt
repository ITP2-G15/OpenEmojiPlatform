package com.platform.openemoji.ui.screens
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Games") }
            )
        }
    ) { innerPadding ->
        Text(text = "Welcome to Games", modifier = androidx.compose.ui.Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun GamesScreenPreview() {
    GamesScreen()
}
