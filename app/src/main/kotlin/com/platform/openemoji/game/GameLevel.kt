package com.platform.openemoji.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object GameLevelsRepository {
    val sampleLevels =
        listOf(
            Level(
                emojiQuestion = "Question 1: What emoji is this 😀 ? More text test:-)",
                alternatives = listOf("Option 1", "Option 2", "Option 3", "Option 4"),
                correctAlternative = 2,
            ),
            Level(
                emojiQuestion = "😎",
                alternatives = listOf("Option A", "Option B", "Option C", "Option D"),
                correctAlternative = 1,
            ),
            // Add more sample levels as needed
        )
}

@Composable
fun GameScreen() {
    val levels = remember { GameLevelsRepository.sampleLevels }

    Column {
        levels.forEach { level ->
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = level.emojiQuestion,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            LevelCard(level = level)
        }
    }
}
