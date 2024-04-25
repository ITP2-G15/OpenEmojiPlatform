package com.platform.openemoji.screens

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.platform.openemoji.R
import com.platform.openemoji.game.GameViewModel
import com.platform.openemoji.header.HeaderLogo

@Composable
fun GameScreen(gameViewModel: GameViewModel) {
    val currentLevel by gameViewModel.currentLevel.collectAsState()
    val levelCounter by gameViewModel.levelCounter.collectAsState()

    val context = LocalContext.current

    val correctAlternativeSound = MediaPlayer.create(context, R.raw.correct_alternativ)
    val wrongAlternativeSound = MediaPlayer.create(context, R.raw.wrong_alternative)

    var wrongAnswers by remember { mutableStateOf(List(4) { false }) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .testTag("gameScreen"),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderLogo()
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(bottom = 16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (currentLevel != null) {
                    Text(
                        text =
                            stringResource(
                                R.string.game_counter,
                                levelCounter + 1,
                            ),
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                    )
                }
                currentLevel?.let {
                    Text(
                        text = it.emojiQuestion,
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier.padding(top = 50.dp),
                    )
                } ?: Text(
                    text = stringResource(R.string.no_games),
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        Box(
            modifier =
                Modifier.padding(horizontal = 8.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                currentLevel?.alternatives?.forEachIndexed { index, alternative ->

                    Button(
                        modifier =
                            Modifier
                                .padding(vertical = 10.dp)
                                .fillMaxWidth(),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    if (wrongAnswers[index]) {
                                        MaterialTheme.colorScheme.error
                                    } else {
                                        MaterialTheme.colorScheme.primary
                                    },
                                contentColor =
                                    if (wrongAnswers[index]) {
                                        MaterialTheme.colorScheme.onError
                                    } else {
                                        MaterialTheme.colorScheme.onPrimary
                                    },
                            ),
                        onClick = {
                            if (currentLevel?.correctAlternative == index) {
                                gameViewModel.incrementLevelCounter()
                                wrongAnswers = List(wrongAnswers.size) { false }
                                correctAlternativeSound.start()
                            } else {
                                wrongAlternativeSound.start()
                                wrongAnswers =
                                    wrongAnswers.toMutableList().apply {
                                        set(
                                            index,
                                            true,
                                        )
                                    }
                            }
                        },
                    ) {
                        Text(
                            text = alternative,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                }
            }
        }
    }
}
