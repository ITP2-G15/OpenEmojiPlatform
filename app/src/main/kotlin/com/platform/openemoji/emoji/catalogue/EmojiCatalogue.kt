package com.platform.openemoji.emoji.catalogue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import com.platform.openemoji.emoji.category.EmojiCategoryViewModel

@Composable
fun EmojiCatalogue(
    emojisByCategory: Map<String, List<Emoji>>,
    navController: NavController,
) {
    Column(
        modifier =
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
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
    categoryViewModel: EmojiCategoryViewModel,
    navController: NavController,
) {
    val overviewEmojisByCategory by catalogueViewModel.overviewEmojisByCategory
        .observeAsState(initial = emptyMap())
    val emojisOfCategory by catalogueViewModel.emojisOfCategory
        .observeAsState()
    val overview = stringResource(R.string.overview)
    val selectedCategory by categoryViewModel.selectedCategory
        .observeAsState(overview)
    categoryViewModel.selectedCategory.observe(
        LocalLifecycleOwner.current,
    ) {
        if (it != overview) {
            catalogueViewModel.loadEmojisOfCategory(it)
        }
    }

    EmojiCatalogue(
        emojisByCategory =
            when (selectedCategory) {
                overview -> overviewEmojisByCategory
                emojisOfCategory?.first ->
                    emojisOfCategory?.let {
                        mapOf(
                            it,
                        )
                    } ?: emptyMap()
                else -> emptyMap()
            },
        navController,
    )
}
