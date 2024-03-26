package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.platform.openemoji.R
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueUi
import com.platform.openemoji.header.HeaderLogo

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.testTag("homeScreen"),
    ) {
        HeaderLogo()
        // Most Popular
        // Made using EmojiCatalogueUI with only one category: "Most Popular"
        EmojiCatalogueUi(
            emojis =
                mapOf(
                    stringResource(R.string.most_popular) to
                        EmojiCatalogue.get().mostPopularEmojis(14),
                ),
            navController = navController,
        )
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
