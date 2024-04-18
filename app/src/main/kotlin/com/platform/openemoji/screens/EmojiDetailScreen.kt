package com.platform.openemoji.screens

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.platform.openemoji.ads.AdSettings
import com.platform.openemoji.ads.InlineAd
import com.platform.openemoji.ads.InterstitialAds
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.IconCopy
import com.platform.openemoji.favorites.FavoritesViewModel
import com.platform.openemoji.favorites.maker.FavoriteMaker
import com.platform.openemoji.navigation.BackButtonNavigation

@Composable
fun EmojiDetailScreen(
    favoritesViewModel: FavoritesViewModel,
    emoji: Emoji,
    navController: NavController,
) {
    val context = LocalContext.current

    // Start loading an interstitial fullscreen ad. Only if this ad is loaded
    // by the time the user presses the return arrow, will the ad be shown.
    val interstitialAd = remember { mutableStateOf<InterstitialAd?>(null) }
    if (AdSettings.get().displayInterstitialAdFromEmojiDetailScreen) {
        LaunchedEffect(LocalLifecycleOwner.current) {
            InterstitialAds.load(context) {
                interstitialAd.value = it
            }
        }
    }
    Column(
        modifier =
            Modifier.testTag(
                "emojiDetailScreen",
            ).verticalScroll(rememberScrollState()),
    ) {
        /**
         * Arrow back button, takes you back to previous location.
         */
        BackButtonNavigation(
            navController = navController,
            modifier = Modifier.testTag("emojiDetailBackButton"),
        ) {
            interstitialAd.value?.show(context as Activity)
        }
        InlineAd()
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = emoji.name,
                    modifier = Modifier.padding(bottom = 12.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                IconCopy(emoji)
            }
        }

        FavoriteMaker(favoritesViewModel, emoji)

        Text(
            text =
                HtmlCompat.fromHtml(
                    emoji.description,
                    HtmlCompat.FROM_HTML_MODE_COMPACT,
                ).toString(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
        )
    }
}
