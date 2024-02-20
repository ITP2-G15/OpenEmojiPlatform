package com.platform.openemoji.emoji.catalogue.grid

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.EmojiIcon

@Composable
fun EmojiGrid(emojis: List<Emoji>) {
    Column {
        emojis.forEach {
            EmojiIcon(emoji = it)
        }
    }
}
