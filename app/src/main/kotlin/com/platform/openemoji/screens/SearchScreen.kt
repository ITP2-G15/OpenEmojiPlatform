package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.platform.openemoji.R
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueUi
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import com.platform.openemoji.emoji.category.CategoryScrollCarousel
import com.platform.openemoji.emoji.category.route

@Composable
fun SearchScreen() {
    val emojiCatalogue = EmojiCatalogue.get()
    // Creates a state for the search text
    val query = remember { mutableStateOf("") }

    Column {
        // Displays a TextField at the top of the screen
        TextField(
            value = query.value,
            onValueChange = { newText ->
                query.value = newText
            },
            label = {
                Text("Search for emoji")
            },
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(12.dp),
        )

        val categoryNav = rememberNavController()

        if (query.value.isNotEmpty()) {
            Column(
                modifier =
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(start = 16.dp, end = 16.dp),
            ) {
                Text(
                    text = "Search results for: ${query.value}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                val filteredEmojis = emojiCatalogue.search(query.value)
                EmojiGrid(emojis = filteredEmojis, navController = categoryNav)
            }
        } else {
            CategoryScrollCarousel(
                categoryNav,
                listOf("All") + emojiCatalogue.categories,
            )
            NavHost(
                navController = categoryNav,
                startDestination = "search/all",
            ) {
                // Categories (All)
                composable(
                    "search/all",
                ) {
                    EmojiCatalogueUi(
                        emojis = emojiCatalogue.byCategory,
                        maxEmojisPerGrid = 15,
                        categoryNav
                    )
                }
                // Subcategories (e.g. Activities > Sport)
                emojiCatalogue.categories.forEach { category ->
                    composable(
                        "search/${route(category)}",
                    ) {
                        emojiCatalogue.bySubCategory(category)?.let {
                            EmojiCatalogueUi(
                                emojis = it,
                                navController = categoryNav
                            )
                        }
                    }
                }
                composable(
                    route = "emoji/{emojiTitle}",
                    arguments = listOf(navArgument("emojiTitle") {
                        type = NavType.StringType
                        nullable = true
                    })
                ){backStackEntry ->  
                    val emojiTitle = backStackEntry.arguments?.getString("emojiTitle") 
                    val emoji = emojiCatalogue.getEmojiByTitle(emojiTitle) 
                    if (emoji != null) {
                        EmojiDetailScreen(emoji = emoji, navController = categoryNav)
                    } else {
                        throw IllegalArgumentException(stringResource(R.string.emoji_not_found))
                    }
                }
            }
        }
    }
}
