import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
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
        // App name
        Text(
            text = "Open Emoji Platform",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )

        val logo = painterResource(R.drawable.ic_launcher_foreground)

        Icon(
            painter = logo,
            contentDescription = "Localized description",
        )
    }
}
