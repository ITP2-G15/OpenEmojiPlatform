package com.platform.openemoji.events

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun EventUi(event: Event) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .size(width = screenWidth, height = 150.dp)
                .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Card(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            colors =
                CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                ),
        ) {
            Text(
                text = event.title,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            )
        }
        Card(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            colors =
                CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                ),
        ) {
            Text(
                text = event.date,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            )
        }
    }
}
