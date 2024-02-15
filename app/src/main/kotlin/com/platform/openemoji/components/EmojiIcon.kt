package com.platform.openemoji.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun EmojiIcon() {
    val imageUrl =
        "https://static-00.iconduck.com/assets.00/" +
            "smiling-face-with-sunglasses-emoji-512x512-7y1eta1y.png"
    AsyncImage(
        model = imageUrl,
        contentDescription = "emoji.title",
        modifier =
            Modifier
                .size(65.dp)
                .padding(top = 10.dp),
    )
}
