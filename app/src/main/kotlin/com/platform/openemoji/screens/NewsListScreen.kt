package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.ads.InlineAd
import com.platform.openemoji.ads.TopBottomAd
import com.platform.openemoji.navigation.BackButtonNavigation
import com.platform.openemoji.news.NewsCard
import com.platform.openemoji.news.NewsViewModel

@Composable
fun NewsListScreen(
    newsViewModel: NewsViewModel,
    navController: NavController,
) {
    val newsList by newsViewModel.latestNews.collectAsState()

    Column(
        modifier =
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 8.dp),
    ) {
        /**
         * Arrow back button, takes you back to previous location.
         */
        BackButtonNavigation(
            navController = navController,
            modifier = Modifier.testTag("newsListBackButton"),
        )
        Text(
            text = stringResource(R.string.latest_news),
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        InlineAd()
        newsList?.let {
            for (news in it) {
                NewsCard(news)
            }
        }
    }
}
