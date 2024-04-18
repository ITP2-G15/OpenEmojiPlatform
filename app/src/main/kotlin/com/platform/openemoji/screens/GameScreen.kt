import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import com.platform.openemoji.game.GameLevel
import com.platform.openemoji.game.GameViewModel

// @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    gameViewModel: GameViewModel,
    navController: NavController? = null,
) {
    val currentLevel by gameViewModel.currentLevel.collectAsState()

    Text(
        text =
            if (navController == null) {
                "Navigation Controller not available"
            } else {
                "This is the Game Screen"
            },
        modifier = Modifier.testTag("gameScreen"),
    )
    currentLevel?.let { GameLevel(it) }
}
