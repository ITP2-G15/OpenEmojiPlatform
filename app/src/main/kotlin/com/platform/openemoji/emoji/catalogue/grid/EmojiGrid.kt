package com.platform.openemoji.emoji.catalogue.grid

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.IconRoute

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EmojiGrid(emojis: List<Emoji>) {
    FlowRow(
        Modifier.padding(bottom = 20.dp),
    ) {
        emojis.forEach {
            IconRoute(emoji = it)
        }
    }
}
