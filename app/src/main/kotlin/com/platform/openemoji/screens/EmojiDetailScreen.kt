package com.platform.openemoji.screens
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import com.platform.openemoji.LocalAnalytics
import com.platform.openemoji.R
import com.platform.openemoji.ads.AdSettings
import com.platform.openemoji.ads.InlineAd
import com.platform.openemoji.ads.InterstitialAd
import com.platform.openemoji.ads.TopBottomAd
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
    val eventIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(emoji.url)) }

    // Record for analytics that this screen was entered and the emoji's name.
    val analytics = LocalAnalytics.current
    LaunchedEffect(analytics) {
        analytics?.track(
            "EnteredEmojiDetails",
            mapOf("emoji name" to emoji.name),
        )
    }

    // Start loading an interstitial fullscreen ad. Only if this ad is loaded
    // by the time the user presses the return arrow, will the ad be shown.
    if (AdSettings.get().displayInterstitialAdFromEmojiDetailScreen) {
        LaunchedEffect(LocalLifecycleOwner.current) {
            InterstitialAd.load(context)
        }
    }
    val scrollState = rememberScrollState()
    val description =
        HtmlCompat.fromHtml(
            emoji.description,
            HtmlCompat.FROM_HTML_MODE_COMPACT,
        ).toString().trim() + "\n"
    val seeMore = stringResource(R.string.see_more_details)
    val styledEmojiDescription =
        buildAnnotatedString {
            withStyle(
                style =
                    MaterialTheme.typography.titleMedium.toSpanStyle().copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
            ) {
                append(description)
            }
            withStyle(
                style =
                    MaterialTheme.typography.titleMedium.toSpanStyle().copy(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline,
                    ),
            ) {
                pushStringAnnotation(tag = seeMore, annotation = seeMore)
                append(seeMore)
            }
        }
    Column(
        modifier =
            Modifier
                .testTag("emojiDetailScreen")
                .verticalScroll(scrollState),
    ) {
        /**
         * Arrow back button, takes you back to previous location.
         */
        BackButtonNavigation(
            navController = navController,
            modifier = Modifier.testTag("emojiDetailBackButton"),
        ) {
            InterstitialAd.show(context)
        }
        TopBottomAd()

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

        ClickableText(
            text = styledEmojiDescription,
            modifier =
                Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 8.dp,
                ),
            onClick = { offset ->
                styledEmojiDescription.getStringAnnotations(offset, offset)
                    .firstOrNull()?.let {
                        context.startActivity(eventIntent)
                    }
            },
        )
        InlineAd()
    }
}
