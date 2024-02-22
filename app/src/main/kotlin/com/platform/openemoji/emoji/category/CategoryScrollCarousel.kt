package com.platform.openemoji.emoji.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun CategoryScrollCarousel(
    navController: NavController,
    categories: List<Category>,
) {
    val currentRoute =
        navController.currentBackStackEntryAsState()
            .value?.destination?.route

    LazyRow {
        items(categories.size) { index ->
            val category = categories[index]
            val isSelected = category.route == currentRoute
            Button(
                onClick = {
                    navController.navigate(category.route)
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
                Text(category.name)
            }
        }
    }
}
