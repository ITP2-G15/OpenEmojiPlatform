package com.platform.openemoji.emoji.catalogue

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid

@Composable
fun EmojiCatalogueComponent(emojis: Map<String, List<Emoji>>) {
    Column {
        emojis.forEach { (category, emojis) ->
            Text(text = category)
            EmojiGrid(emojis = emojis)
        }
    }
}
