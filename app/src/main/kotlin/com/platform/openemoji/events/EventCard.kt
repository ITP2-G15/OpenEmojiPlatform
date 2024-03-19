package com.platform.openemoji.events

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun EventCard(event: Event) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val context = LocalContext.current
    val eventIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(event.url)) }

    val painter =
        rememberImagePainter(
            data = event.img,
            builder = {
                crossfade(true) // Enable crossfade animation
            },
        )

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .size(width = screenWidth, height = 150.dp)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clickable {
                    context.startActivity(eventIntent)
                },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            // Background image
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
            Column {
                Card(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    colors =
                        CardDefaults.cardColors(
                            containerColor =
                                MaterialTheme.colorScheme.secondary.copy(
                                    alpha = 0.7f,
                                ),
                            contentColor = MaterialTheme.colorScheme.onPrimary,
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
                            containerColor =
                                MaterialTheme.colorScheme.secondary.copy(
                                    alpha = 0.7f,
                                ),
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                ) {
                    Text(
                        text = event.date,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    )
                }
            }
        }
    }
}
