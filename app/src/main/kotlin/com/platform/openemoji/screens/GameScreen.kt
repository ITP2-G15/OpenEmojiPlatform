import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

// @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController? = null) {
   /* Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Games") }
            )
        }
    ) { innerPadding ->
        Text(text = "Welcome to Games",
            modifier = Modifier.padding(innerPadding)
        )
    }*/
    Text("This is the Game Screen")
}

@Preview(showBackground = true)
@Composable
fun GamesScreenPreview() {
    GameScreen()
}
