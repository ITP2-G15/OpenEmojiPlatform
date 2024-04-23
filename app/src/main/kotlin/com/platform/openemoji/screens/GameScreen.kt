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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.platform.openemoji.game.GameViewModel
import com.platform.openemoji.header.HeaderLogo

@Composable
fun GameScreen(gameViewModel: GameViewModel) {
    val currentLevel by gameViewModel.currentLevel.collectAsState()
    val levelCounter by gameViewModel.levelCounter.collectAsState()

    val context = LocalContext.current

    val correctAlternativSound =
        MediaPlayer.create(
            context,
            "File:///assets/SFX/correctAlternativ.mp3",
        )
    val wrongAlternativSound = MediaPlayer.create(context, R.raw.audio)

    var wrongAnswers by remember { mutableStateOf(List(4) { false }) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderLogo()
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(bottom = 16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Level $levelCounter \n",
                    style = TextStyle(fontSize = 40.sp),
                    fontWeight = FontWeight.Bold,
                )
                currentLevel?.let {
                    Text(
                        text = it.emojiQuestion,
                        style = TextStyle(fontSize = 50.sp),
                    )
                }
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
                                .fillMaxWidth(1f),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    if (wrongAnswers[index]) {
                                        MaterialTheme.colorScheme.error
                                    } else {
                                        MaterialTheme.colorScheme.primary
                                    },
                            ),
                        onClick = {
                            if (currentLevel?.correctAlternative == index) {
                                gameViewModel.incrementLevelCounter()
                                wrongAnswers = List(wrongAnswers.size) { false }
                            } else {
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
                            style =
                                TextStyle(
                                    fontSize = 25.sp,
                                ),
                        )
                    }
                }
            }
        }
    }
}
