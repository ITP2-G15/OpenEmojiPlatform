package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.ads.InlineAd
import com.platform.openemoji.ads.TopBottomAd
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueViewModel
import com.platform.openemoji.events.EventViewModel
import com.platform.openemoji.events.UpcomingEvents
import com.platform.openemoji.header.HeaderLogo
import com.platform.openemoji.news.LatestNews
import com.platform.openemoji.news.NewsViewModel

@Composable
fun HomeScreen(
    emojiCatalogueViewModel: EmojiCatalogueViewModel,
    eventViewModel: EventViewModel,
    newsViewModel: NewsViewModel,
    navController: NavController,
) {
    val mostPopularEmojis by emojiCatalogueViewModel.mostPopularEmojis.collectAsState()
    LaunchedEffect(LocalLifecycleOwner.current) {
        emojiCatalogueViewModel.loadMostPopularEmojis(14)
    }

    Column(
        modifier =
            Modifier
                .testTag("homeScreen")
                .verticalScroll(rememberScrollState()),
    ) {
        TopBottomAd()
        HeaderLogo()
        // Most Popular
        // Made using EmojiCatalogue with only one category: "Most Popular"
        EmojiCatalogue(
            mapOf(
                stringResource(R.string.most_popular) to
                    mostPopularEmojis,
            ),
            emojisOfCategoryAreLoading = { mostPopularEmojis.isEmpty() },
            navController = navController,
        )
        UpcomingEvents(eventViewModel, navController)

        InlineAd()

        LatestNews(newsViewModel, navController)
    }
}
