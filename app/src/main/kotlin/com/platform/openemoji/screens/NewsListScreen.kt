package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.navigation.BackButtonNavigation
import com.platform.openemoji.news.News
import com.platform.openemoji.news.NewsCard

@Composable
fun NewsListScreen(navController: NavController) {
    // TODO replace data
    val allNewsData =
        listOf(
            News(
                "First Look: New Emojis in iOS 17.4",
                "https://blog.emojipedia.org/first-look-new-emojis-in-ios-17-4/",
                "https://blog.emojipedia.org/content/images/size/w2000/2024/01/" +
                    "Emojipedia-iOS-Apple-Emoji-15_0-Header.jpg",
                "New emojis have arrived on iOS as part of the first iOS 17.4 " +
                    "beta. The new additions include a phoenix, a lime, smileys " +
                    "shaking their heads up and down, and a series of " +
                    "direction-specifying people emojis.",
            ),
            News(
                "Google's Emoji 15.1 Support In Noto Color Emoji",
                "https://blog.emojipedia.org/googles-" +
                    "emoji-15-1-support-in-noto-color-emoji/",
                "https://blog.emojipedia.org/content/images/size/w2000/2023/11" +
                    "/Emojipedia-Google-Emoji-15_1-Header.jpg",
                "Today Google has officially unveiled its full-color designs for " +
                    "Unicode's latest approved emojis, which include a phoenix, a " +
                    "lime, smileys shaking their heads up and down, and a series " +
                    "of direction-specifying people emojis.",
            ),
            News(
                "Microsoft Windows 11 23H2 Emoji Changelog",
                "https://blog.emojipedia.org/microsoft-windows-11-23h2-emoji-changelog/",
                "https://blog.emojipedia.org/content/images/size/w2000/" +
                    "2023/11/Emojipedia-Windows-11-23H2-Header.jpg",
                "Microsoft have begun to roll out their latest update to Windows 11," +
                    " adding Emoji 15.0 support and debuting the glossy 3D Fluent" +
                    " designs in select applications.",
            ),
        )

    Column(
        modifier =
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 8.dp),
    ) {
        /**
         * Arrow back button, takes you back to previous location.
         */
        BackButtonNavigation(navController)

        Text(
            text = stringResource(R.string.latest_news),
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        for (news in allNewsData) {
            NewsCard(news)
        }
    }
}
