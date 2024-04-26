package com.platform.openemoji.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.platform.openemoji.R

@Composable
fun GameQuestionCard(
    currentLevel: Level,
    levelCounter: Int,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(250.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .padding(26.dp)
                    .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text =
                    stringResource(
                        R.string.game_counter,
                        levelCounter + 1,
                    ),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = currentLevel.emojiQuestion,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(top = 50.dp),
                maxLines = 1,
            )
        }
    }
}
