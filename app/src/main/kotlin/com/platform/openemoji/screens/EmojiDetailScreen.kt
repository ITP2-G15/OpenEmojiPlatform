package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.IconCopy

@Composable
fun EmojiDetailScreen(
    emoji: Emoji,
    navController: NavController,
) {
    Column(
        modifier = Modifier.testTag("emojiDetailScreen"),
    ) {
        /**
         * Arrow back button, takes you back to previous location.
         */
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = stringResource(R.string.back_arrow),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp),
            )
        }
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = emoji.name,
                    modifier = Modifier.padding(bottom = 12.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                IconCopy(emoji)
            }
        }

        Text(
            text =
                HtmlCompat.fromHtml(
                    emoji.description,
                    HtmlCompat.FROM_HTML_MODE_COMPACT,
                ).toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
        )
    }
}
