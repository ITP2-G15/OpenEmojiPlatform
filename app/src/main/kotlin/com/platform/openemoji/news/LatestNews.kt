package com.platform.openemoji.news

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun LatestNews(navController: NavController) {
    val news =
        News(
            "First Look: New Emojis in iOS 17.4",
            "https://blog.emojipedia.org/first-look-new-emojis-in-ios-17-4/",
            "https://blog.emojipedia.org/content/images/size/w2000/2024/01/Emojipedia-iOS-Apple-Emoji-15_0-Header.jpg",
            "New emojis have arrived on iOS as part of the first iOS 17.4 beta. The new additions include a phoenix, a lime, smileys shaking their heads up and down, and a series of direction-specifying people emojis.",
        )

    NewsCard(news)
}
