import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.platform.openemoji.R

@Composable
fun AppHeader() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // App logo
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(80.dp), // Adjust the size as needed
        )

        Spacer(modifier = Modifier.height(16.dp))

        // App name
        Text(
            text = "Open Emoji Platform",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp, // Adjust the font size as needed
        )
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        // Include the app header at the top of the screen
        AppHeader()

        // Add other content below the header
        // For example:
        Text(text = "Hello, World!")
    }
}
