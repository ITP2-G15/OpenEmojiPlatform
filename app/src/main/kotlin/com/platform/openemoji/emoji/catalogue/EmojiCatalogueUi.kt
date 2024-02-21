@file:JvmName("EmojiCatalogueKt")

package com.platform.openemoji.emoji.catalogue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid
import kotlin.math.min

@Composable
fun EmojiCatalogueUi(
    emojis: Map<String, List<Emoji>>,
    maxEmojisPerGrid: Int? = null,
) {
    Column(
        modifier =
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp),
    ) {
        emojis.forEach { (category, emojis) ->
            Text(
                text = category,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
            // Limit number of emojis displayed in each grid
            val emojisToDisplay =
                if (maxEmojisPerGrid == null) {
                    emojis
                } else {
                    emojis.subList(0, min(emojis.size, maxEmojisPerGrid))
                }

            EmojiGrid(emojis = emojisToDisplay)
        }
    }
}
