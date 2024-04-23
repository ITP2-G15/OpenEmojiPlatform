package com.platform.openemoji.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameLevel(currentLevel: Level) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = currentLevel.emojiQuestion,
            style =
                TextStyle(
                    fontSize = 70.sp,
                ),
            modifier =
                Modifier.align(
                    Alignment.TopCenter,
                )
                    .padding(20.dp),
        )
    }
}
