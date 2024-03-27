package com.platform.openemoji.news

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun NewsCard(news: News) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val context = LocalContext.current
    val newsIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(news.url)) }

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .size(width = screenWidth, height = 200.dp)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .testTag("newsCard")
                .clickable {
                    context.startActivity(newsIntent)
                },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = news.image,
                    contentDescription = news.name,
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                )
                Card(
                    modifier =
                        Modifier
                            .size(width = screenWidth, height = 90.dp)
                            .align(Alignment.BottomStart),
                    shape = RoundedCornerShape(2.dp),
                    colors =
                        CardDefaults.cardColors(
                            containerColor =
                                MaterialTheme.colorScheme.surfaceContainer.copy(
                                    0.9f,
                                ),
                            contentColor = MaterialTheme.colorScheme.onBackground,
                        ),
                ) {
                    Text(
                        text = news.name,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 3.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = news.name,
                        modifier = Modifier.padding(horizontal = 12.dp),
                        maxLines = 2,
                    )
                }
            }
        }
    }
}
