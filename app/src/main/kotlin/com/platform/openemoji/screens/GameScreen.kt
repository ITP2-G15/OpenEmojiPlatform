package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.platform.openemoji.game.GameLevel
import com.platform.openemoji.game.GameViewModel
import com.platform.openemoji.header.HeaderLogo

@Composable
fun GameScreen(gameViewModel: GameViewModel) {
    val currentLevel by gameViewModel.currentLevel.collectAsState()
    val levelCounter by gameViewModel.levelCounter.collectAsState()

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
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Level $levelCounter \n",
                    style = TextStyle(fontSize = 40.sp),
                    fontWeight = FontWeight.Bold,
                )
                currentLevel?.let { GameLevel(it) }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

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
                        onClick = {
                            if (currentLevel?.correctAlternative == index) {
                                gameViewModel.incrementLevelCounter()
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
