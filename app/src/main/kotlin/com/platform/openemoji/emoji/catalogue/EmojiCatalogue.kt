package com.platform.openemoji.emoji.catalogue

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid

@Composable
fun EmojiCatalogue(
    emojisByCategory: Map<String, List<Emoji>>,
    navController: NavController,
    modifier: Modifier = Modifier,
    emojisOfCategoryAreLoading: (String) -> Boolean = { false },
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        emojisByCategory.forEach { (category, emojis) ->
            Text(
                text = category,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier =
                    Modifier.semantics { heading() }.testTag(
                        "catalogueCategoryHeader",
                    ),
            )
            if (emojisOfCategoryAreLoading(category)) {
                SpinLoader()
            } else {
                EmojiGrid(emojis, navController)
            }
        }
    }
}

// Version that supports the whole catalogue loading, not just the individual categories' emojis.
@Composable
fun EmojiCatalogue(
    emojisByCategory: Map<String, List<Emoji>>,
    navController: NavController,
    catalogueIsLoading: Boolean,
    modifier: Modifier = Modifier,
    emojisOfCategoryAreLoading: (String) -> Boolean = { false },
) {
    if (catalogueIsLoading) {
        SpinLoader()
    } else {
        EmojiCatalogue(
            emojisByCategory,
            navController,
            emojisOfCategoryAreLoading = emojisOfCategoryAreLoading,
            modifier = modifier,
        )
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
        catalogueIsLoading = selectedCategoryEmojis == null,
        modifier =
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 10.dp),
    )
}

@Composable
private fun SpinLoader() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
