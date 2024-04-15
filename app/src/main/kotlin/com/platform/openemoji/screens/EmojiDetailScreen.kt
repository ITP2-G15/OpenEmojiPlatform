package com.platform.openemoji.screens

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.platform.openemoji.ads.AdSettings
import com.platform.openemoji.ads.InlineAd
import com.platform.openemoji.ads.loadInterstitialAd
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.IconCopy
import com.platform.openemoji.navigation.BackButtonNavigation

@Composable
fun EmojiDetailScreen(
    emoji: Emoji,
    navController: NavController,
) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    // Start loading an interstitial fullscreen ad. Only if this ad is loaded
    // by the time the user presses the return arrow, will the ad be shown.
    val interstitialAd = remember { mutableStateOf<InterstitialAd?>(null) }
    if (AdSettings.get().displayInterstitialAdFromEmojiDetailScreen) {
        LaunchedEffect(LocalLifecycleOwner.current) {
            loadInterstitialAd(context) {
                interstitialAd.value = it
            }
        }
    }
    Column(
        modifier = Modifier.testTag("emojiDetailScreen"),
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

        Text(
            text =
                HtmlCompat.fromHtml(
                    emoji.description,
                    HtmlCompat.FROM_HTML_MODE_COMPACT,
                ).toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
        )
        Text(
            text = "Visit for more info",
            style = MaterialTheme.typography.bodyMedium,
            modifier =
                Modifier
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .clickable {
                        uriHandler.openUri(emoji.url)
                    },
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
