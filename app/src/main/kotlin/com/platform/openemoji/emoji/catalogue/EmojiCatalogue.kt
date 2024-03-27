package com.platform.openemoji.emoji.catalogue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid

@Composable
fun EmojiCatalogue(
    emojisByCategory: Map<String, List<Emoji>>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        emojisByCategory.forEach { (category, emojis) ->
            Text(
                text = category,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.testTag("catalogueCategoryHeader"),
            )

            EmojiGrid(emojis, navController)
        }
    }
}

@Composable
fun SearchScreenEmojiCatalogue(
    catalogueViewModel: EmojiCatalogueViewModel,
    navController: NavController,
) {
    val selectedCategoryEmojis by catalogueViewModel.selectedCategoryEmojis
        .collectAsState()

    EmojiCatalogue(
        emojisByCategory = selectedCategoryEmojis ?: emptyMap(),
        navController,
        modifier = Modifier.verticalScroll(rememberScrollState()),
    )
}
