package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.IconCopy
import com.platform.openemoji.navigation.BackButtonNavigation

@Composable
fun EmojiDetailScreen(
    emoji: Emoji,
    navController: NavController,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Column(
        modifier = Modifier.testTag("emojiDetailScreen"),
    ) {
        /**
         * Arrow back button, takes you back to previous location.
         */
        BackButtonNavigation(navController)

        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .size(width = screenWidth, height = 150.dp)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconCopy(emoji)
                // Added padding to hinder Text and IconCopy to collide.
                Text(
                    text = emoji.title,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Text(
            text = emoji.description,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
        )
    }
}
