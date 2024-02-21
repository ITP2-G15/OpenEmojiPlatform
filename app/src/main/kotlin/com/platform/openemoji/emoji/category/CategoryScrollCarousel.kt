package com.platform.openemoji.emoji.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

/* This could potentially generate the same route from different category names,
    but this won't realistically happen with our data.
*/
fun route(category: String): String =
    category.lowercase()
        .replace(Regex("[^a-zA-Z ]"), "")
        .replace(Regex(" +"), "-")

@Composable
fun CategoryScrollCarousel(
    navController: NavController,
    categories: List<String>,
) {
    val currentRoute =
        navController.currentBackStackEntryAsState()
            .value?.destination?.route

    LazyRow(
        Modifier.padding(bottom = 10.dp),
    ) {
        items(categories.size) { index ->
            val category = categories[index]
            val isSelected = "search/${route(category)}" == currentRoute
            Button(
                onClick = {
                    navController.navigate("search/${route(category)}")
                },
                modifier = Modifier.padding(horizontal = 4.dp),
                contentPadding = PaddingValues(8.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            if (isSelected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.secondary
                            },
                    ),
            ) {
                Text(
                    category,
                    style = MaterialTheme.typography.labelMedium,
                    color =
                        if (isSelected) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.onSecondary
                        },
                )
            }
        }
    }
}
