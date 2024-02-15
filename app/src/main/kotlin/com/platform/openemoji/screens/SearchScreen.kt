package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.platform.openemoji.components.Category
import com.platform.openemoji.components.CategoryScrollCarousel

@Composable
fun CategoryGrid(name: String) {
    Button(onClick = {}) {
        Text(name)
    }
}

@Composable
fun SearchScreen() {
    val categoryList =
        listOf(
            Category("All", "all"),
            Category("Smileys & Emotion", "smileys"),
            Category("People & Body", "people"),
            Category("Animals & Nature", "smileys"),
            Category("Food & Drink", "smileys"),
            Category("Travel & Places", "smileys"),
            Category("Activities", "smileys"),
            Category("Objects", "smileys"),
            Category("Symbols", "smileys"),
            Category("Flags", "smileys"),
        )

    Column(modifier = Modifier.padding(16.dp)) {
        val navController = rememberNavController()
        CategoryScrollCarousel(navController, categoryList)
        NavHost(navController = navController, startDestination = "all") {
            composable("all") {
                CategoryGrid(
                    "all",
                ) // TODO: Create CategoryGrid which displays emojis for categories.
            }
            composable("smileys") {
                CategoryGrid(
                    "smileys",
                ) // TODO: Create CategoryGrid which displays emojis for categories.
            }
        }
    }
}
