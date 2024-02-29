package com.platform.openemoji.emoji.catalogue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import kotlin.math.min

@Composable
fun EmojiCatalogueUi(
    emojis: Map<String, List<Emoji>>,
    filterText: String,
    maxEmojisPerGrid: Int? = null,
) {
    Column(
        modifier =
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp),
    ) {
        emojis.forEach { (category, emojis) ->
            // Filter the emojis based on the filterText (often from searchbar input)
            // This filters the emojis based on the title of the emoji
            val filteredEmojis =
                emojis.filter {
                    it.title.contains(
                        filterText,
                        ignoreCase = true,
                    )
                }
            // Only display the category if it has any emojis that match the filter
            if (filteredEmojis.isNotEmpty()) {
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.testTag("catalogueUiCategoryHeader"),
                )
                // Limit number of emojis displayed in each grid
                val emojisToDisplay =
                    if (maxEmojisPerGrid == null) {
                        filteredEmojis
                    } else {
                        filteredEmojis.subList(
                            0,
                            min(filteredEmojis.size, maxEmojisPerGrid),
                        )
                    }

                EmojiGrid(emojis = emojisToDisplay) // Display the filtered emojis
            }
        }
    }
}
