package com.platform.openemoji.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.IconCopy

@Composable
fun EmojiScreen(/*emoji: Emoji*/) {
    Column() {
        IconButton(onClick = {/*TODO*/}) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "back",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )
        }
        Card(
            border = BorderStroke(2.dp,MaterialTheme.colorScheme.primary),
        ) {
            //IconCopy(emoji)
            //emoji.title
            Row {
                Text("This is where the emoji picture + copy should be")
                Text("EmojiName")
            }
        }
        Text("Description:")
    }
}