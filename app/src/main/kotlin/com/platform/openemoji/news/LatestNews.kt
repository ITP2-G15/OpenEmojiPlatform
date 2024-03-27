package com.platform.openemoji.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.navigation.Screen
import com.platform.openemoji.navigation.ShowMoreNavigation

@Composable
fun LatestNews(navController: NavController) {
    // TODO replace data
    val latestNewsData =
        listOf(
            News(
                "First Look: New Emojis in iOS 17.4",
                "https://blog.emojipedia.org/first-look-new-emojis-in-ios-17-4/",
                "https://blog.emojipedia.org/content/images/size/" +
                    "w2000/2024/01/Emojipedia-iOS-Apple-Emoji-15_0-Header.jpg",
                "New emojis have arrived on iOS as part of the first iOS 17.4 beta." +
                    " The new additions include a phoenix, a lime, smileys shaking" +
                    " their heads up and down, and a series of direction-specifying" +
                    " people emojis.",
            ),
            News(
                "Google's Emoji 15.1 Support In Noto Color Emoji",
                "https://blog.emojipedia.org/googles-emoji" +
                    "-15-1-support-in-noto-color-emoji/",
                "https://blog.emojipedia.org/content/images/size/w2000/" +
                    "2023/11/Emojipedia-Google-Emoji-15_1-Header.jpg",
                "Today Google has officially unveiled its full-color des" +
                    "igns for Unicode's latest approved emojis, which include" +
                    " a phoenix, a lime, smileys shaking their heads up and down, " +
                    "and a series of direction-specifying people emojis.",
            ),
        )

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
            )
            ShowMoreNavigation(
                navController = navController,
                screen = Screen.NewsListScreen,
                modifier = Modifier.testTag("showMoreLatestNews"),
            )
        }
        for (news in latestNewsData) {
            NewsCard(news)
        }
    }
}
