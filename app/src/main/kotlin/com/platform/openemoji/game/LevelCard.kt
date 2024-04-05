package com.platform.openemoji.game

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LevelCard(level: Level) {
    val selectedOptionIndex = remember { mutableStateOf<Int?>(null) }
    val levelsIntent = remember { Intent(Intent.ACTION_VIEW) }
    val context = LocalContext.current

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .testTag("levelsCard"),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        level.alternatives.forEachIndexed { index, alternative ->
            val isCorrect = index == level.correctAlternative
            val isSelected = selectedOptionIndex.value == index

            // Determine card color based on correctness and selection
            val cardColor =
                if (isSelected) {
                    if (isCorrect) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.secondary
                    }
                } else {
                    if (isCorrect) {
                        MaterialTheme.colorScheme.tertiaryContainer
                    } else {
                        MaterialTheme.colorScheme.onError
                    }
                }

            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Handle click event here
                            selectedOptionIndex.value = index
                        },
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    // backgroundColor = cardColor,
                ) {
                    Text(
                        text = alternative,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
    }
}
/*
@Composable
fun LevelCard(level: Level) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val context = LocalContext.current
    val levelsIntent = remember { Intent(Intent.ACTION_VIEW) }
    val levels = remember { GameLevelsRepository.sampleLevels }

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .testTag("levelsCard"),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        levels.find { it == level }?.alternatives?.forEachIndexed { index, alternative ->
            val isCorrect = index == level.correctAlternative

            // Determine card color based on correctness
            val cardColor =
                if (isCorrect) {
                    MaterialTheme.colorScheme.tertiaryContainer
                } else {
                    MaterialTheme.colorScheme.onError
                }

            Card(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Start activity or perform action when card is clicked
                            context.startActivity(levelsIntent)
                        },
                // backgroundColor = cardColor,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = alternative,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
    }
}
*/
