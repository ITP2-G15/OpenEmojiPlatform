package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun EmojiScreen(sourcePage: String) {
    Column() {
        IconButton(onClick = {/*TODO*/}) {
            Icon(
                Icons.Filled.Favorite
                tint = Color.Red,
                contentDescription = "favorite",
                modifier = Modifier.size(48.dp)
            )
        }

        }
    }
}