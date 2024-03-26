package com.platform.openemoji.screens

import HeaderLogo
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.platform.openemoji.R
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueViewModel

@Composable
fun HomeScreen(
    emojiCatalogueViewModel: EmojiCatalogueViewModel,
    navController: NavController,
) {
    val mostPopularEmojis by emojiCatalogueViewModel.mostPopularEmojis.observeAsState()
    LaunchedEffect(LocalLifecycleOwner.current) {
        emojiCatalogueViewModel.loadMostPopularEmojis(14)
    }

    Column(
        modifier = Modifier.testTag("homeScreen"),
    ) {
        HeaderLogo()
        // Most Popular
        // Made using EmojiCatalogueUI with only one category: "Most Popular"
        mostPopularEmojis?.let {
            EmojiCatalogue(
                mapOf(
                    stringResource(R.string.most_popular) to
                        it,
                ),
                navController = navController,
            )
        }
        AndroidView(
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = "ca-app-pub-3940256099942544/6300978111"
                    loadAd(AdRequest.Builder().build())
                }
            },
        )
    }
}
