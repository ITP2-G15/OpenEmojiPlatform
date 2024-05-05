package com.platform.openemoji.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.navigation.Screen
import com.platform.openemoji.navigation.ShowMoreNavigation

const val NEWS_IN_LATEST_NEWS = 2

@Composable
fun LatestNews(
    newsViewModel: NewsViewModel,
    navController: NavController,
) {
    val latestNews by newsViewModel.latestNews.collectAsState()

    Column {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.latest_news),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.semantics { heading() },
            )
            ShowMoreNavigation(
                navController = navController,
                screen = Screen.NewsListScreen,
                modifier = Modifier.testTag("showMoreLatestNews"),
            )
        }
        latestNews?.let {
            for (news in it.take(NEWS_IN_LATEST_NEWS)) {
                NewsCard(news)
            }
        }
    }
}
