package com.platform.openemoji.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.padding(top = 50.dp),
        ) {
            Text(
                text = currentLevel.emojiQuestion,
                style =
                    TextStyle(
                        fontSize = 80.sp,
                    ),
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}
