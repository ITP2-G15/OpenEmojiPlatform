import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.platform.openemoji.game.GameLevel
import com.platform.openemoji.game.GameViewModel

@Composable
fun GameScreen(
    gameViewModel: GameViewModel,
    navController: NavController? = null,
) {
    val currentLevel by gameViewModel.currentLevel.collectAsState()
    val levelCounter by gameViewModel.levelCounter.collectAsState()

    // var answeredCorrectly by remember { mutableStateOf(false) }

    // val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardHeight = 400.dp
    // val wrongAnswerColor = MaterialTheme.colorScheme.error

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(cardHeight)
                    .padding(bottom = 16.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Level $levelCounter",
                    style = TextStyle(fontSize = 70.sp),
                )
                currentLevel?.let { GameLevel(it) }
            }
        }

        Box(
            modifier = Modifier.padding(horizontal = 8.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                currentLevel?.alternatives?.forEachIndexed { index, alternative ->

                    Button(
                        modifier =
                            Modifier.padding(
                                vertical = 10.dp,
                            ),
                        onClick = {
                            if (currentLevel?.correctAlternative == index) {
                                println("Riktig")
                                gameViewModel.incrementLevelCounter()
                            } else {
                                println("Feil")
                            }
                        },
                    ) {
                        Text(text = alternative)
                    }
                }
            }
        }
    }
}
