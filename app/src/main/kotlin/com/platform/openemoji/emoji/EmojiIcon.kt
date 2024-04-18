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
import androidx.navigation.NavController
import com.platform.openemoji.LocalAnalytics
import com.platform.openemoji.R
import com.platform.openemoji.navigation.Screen

// This will be used for the emoji grid since it routes to the emoji icon details view
@Composable
fun IconRoute(
    emoji: Emoji,
    navController: NavController,
) {
    Text(
        emoji.code,
        style = MaterialTheme.typography.displaySmall,
        modifier =
            Modifier.clickable {
                navController.navigate(
                    Screen.EmojiDetailScreen.withArgs(emoji.name),
                )
            }.padding(4.dp).testTag("iconRoute"),
    )
}

// This will be used for the emoji icon details view since it used to copy the emoji
@Composable
fun IconCopy(emoji: Emoji) {
    val clipboardManager = LocalClipboardManager.current
    val analytics = LocalAnalytics.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            emoji.code,
            style = MaterialTheme.typography.displayLarge,
            modifier =
                Modifier.clickable {
                    clipboardManager.setText(AnnotatedString(emoji.code))
                    analytics?.track(
                        "CopiedEmoji",
                        mapOf("emoji name" to emoji.name),
                    )
                },
        )
        Button(
            onClick = {
                clipboardManager.setText(AnnotatedString(emoji.code))
            },
            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        MaterialTheme.colorScheme.primary,
                ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.testTag("emojiIconCopyButton"),
        ) {
            Icon(
                Icons.Default.ContentCopy,
                contentDescription = stringResource(R.string.copy_emoji_description),
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
