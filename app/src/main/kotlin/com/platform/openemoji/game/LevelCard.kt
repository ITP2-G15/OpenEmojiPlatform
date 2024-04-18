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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LevelCard(
    level: Level,
    onAnswerSelected: (Boolean) -> Unit
){
    val selectedOptionIndex = remember { mutableStateOf<Int?>(null) }
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

            // Right or wrong (color card)
            val cardColor =
                if (isSelected) {
                    if (isCorrect) {
                        MaterialTheme.colorScheme.tertiaryContainer
                    } else {
                        MaterialTheme.colorScheme.onError }
                    }
                else {
                    Color.Transparent
                }

            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Handle click event here
                            selectedOptionIndex.value = index
                            val isAnswerCorrect = isCorrect && isSelected
                            onAnswerSelected(isAnswerCorrect)
                        },
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    //backgroundColor = cardColor,
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
