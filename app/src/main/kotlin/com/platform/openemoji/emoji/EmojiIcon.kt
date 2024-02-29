package com.platform.openemoji.emoji

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.platform.openemoji.R

// This will only be used for design variants in the emoji icon view if we have time to implement that
@Composable
fun IconDesign(emoji: Emoji) {
    AsyncImage(
        model = emoji.imageUrl,
        contentDescription = emoji.title,
        modifier =
            Modifier
                .size(65.dp)
                .padding(top = 10.dp),
    )
}

// This will be used for the emoji grid since it routes to the emoji icon details view
@Composable
fun IconRoute(emoji: Emoji) {
    val navController = rememberNavController()
    Text(
        emoji.emojiCode,
        style = MaterialTheme.typography.displaySmall,
        modifier =
            Modifier.clickable {
                // navController.navigate("emoji/${emoji.title}")
            }.padding(4.dp).testTag("iconRoute"),
    )
}

// This will be used for the emoji icon details view since it used to copy the emoji
@Composable
fun IconCopy(emoji: Emoji) {
    val clipboardManager = LocalClipboardManager.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            emoji.emojiCode,
            style = MaterialTheme.typography.displayLarge,
            modifier =
                Modifier.clickable {
                    clipboardManager.setText(AnnotatedString(emoji.emojiCode))
                },
        )
        Button(
            onClick = {
                clipboardManager.setText(AnnotatedString(emoji.emojiCode))
            },
            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        MaterialTheme.colorScheme.primary,
                ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Icon(
                Icons.Default.ContentCopy,
                contentDescription = stringResource(R.string.copy_icon_description),
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                stringResource(R.string.copy),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(start = 4.dp),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}
