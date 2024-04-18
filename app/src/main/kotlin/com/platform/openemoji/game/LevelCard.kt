package com.platform.openemoji.game

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LevelCard(level: Level) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = level.emojiQuestion,
        )
    }
}
