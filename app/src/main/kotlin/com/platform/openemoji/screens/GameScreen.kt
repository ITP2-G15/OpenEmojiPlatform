package com.platform.openemoji.screens

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.platform.openemoji.R
import com.platform.openemoji.game.GameAlternativeButton
import com.platform.openemoji.game.GameQuestionCard
import com.platform.openemoji.game.GameViewModel
import com.platform.openemoji.header.HeaderLogo

@Composable
fun GameScreen(gameViewModel: GameViewModel) {
    val currentLevel by gameViewModel.currentLevel.collectAsState()
    val levelCounter by gameViewModel.levelCounter.collectAsState()

    // SFX when user presses button
    val context = LocalContext.current
    val correctAlternativeSound = MediaPlayer.create(context, R.raw.correct_alternativ)
    val wrongAlternativeSound = MediaPlayer.create(context, R.raw.wrong_alternative)

    // list is used to display right color. Not sure if it is the best way to do this.
    var wrongAnswers by remember { mutableStateOf(List(4) { false }) }

    Column(
        modifier =
            Modifier
                .padding(horizontal = 12.dp)
                .testTag("gameScreen")
                .verticalScroll(rememberScrollState()),
    ) {
        HeaderLogo()
        currentLevel?.let { GameQuestionCard(it, levelCounter) }
            ?: Text(
                text = stringResource(R.string.no_games),
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
        currentLevel?.alternatives?.forEachIndexed { index, alternative ->
            GameAlternativeButton(
                alternative = alternative,
                onClick = {
                    if (currentLevel?.correctAlternative == index) {
                        gameViewModel.incrementLevelCounter()
                        wrongAnswers = List(wrongAnswers.size) { false }
                        correctAlternativeSound.start()
                    } else {
                        wrongAnswers =
                            wrongAnswers.toMutableList().apply {
                                set(index, true)
                            }
                        wrongAlternativeSound.start()
                    }
                },
                isWrongAnswer = wrongAnswers[index],
            )
        }
    }
}
