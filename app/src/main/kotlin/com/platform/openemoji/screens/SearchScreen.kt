package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.platform.openemoji.emoji.category.Category
import com.platform.openemoji.emoji.category.CategoryScrollCarousel

@Composable
fun CategoryGrid(name: String) {
    Text(name)
}

@Composable
fun SearchScreen() {
    val categoryList =
        listOf(
            Category("All", "all"),
            Category("Smileys & Emotion", "smileys"),
            Category("People & Body", "people"),
            Category("Animals & Nature", "animals"),
            Category("Food & Drink", "food"),
            Category("Travel & Places", "travel"),
            Category("Activities", "activities"),
            Category("Objects", "object"),
            Category("Symbols", "symbols"),
            Category("Flags", "flags"),
        )

    //Creates a state for the search text
    val searchText = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        //Displays a TextField at the top of the screen
        TextField(
            value = searchText.value,
            onValueChange = { newText -> searchText.value = newText },
            label = { Text("Search for emoji") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(12.dp)
        )

        val categoryNav = rememberNavController()
        CategoryScrollCarousel(categoryNav, categoryList)
        NavHost(navController = categoryNav, startDestination = "all") {
            categoryList.forEach { category ->
                composable(category.route) {
                    CategoryGrid(category.name)
                }
            }
        }
    }
}