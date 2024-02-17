package com.platform.openemoji.emoji

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun EmojiIcon(emoji: Emoji) {
    AsyncImage(
        model = emoji.imageUrl,
        contentDescription = emoji.title,
        modifier =
            Modifier
                .size(65.dp)
                .padding(top = 10.dp),
    )
}
