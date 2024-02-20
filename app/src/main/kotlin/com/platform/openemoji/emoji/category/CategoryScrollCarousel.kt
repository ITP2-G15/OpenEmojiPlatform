package com.platform.openemoji.emoji.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

@Composable
fun CategoryScrollCarousel(
    navController: NavController,
    categories: List<Category>,
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
                Text(category)
            }
        }
        Box(
            modifier =
                Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.onBackground
                            .copy(alpha = 0.25f),
                    ),
        )
    }
}
