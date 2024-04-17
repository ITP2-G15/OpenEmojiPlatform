package com.platform.openemoji.ads
import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

fun loadInterstitialVideoAd(
    context: Context,
    onLoaded: (loadedAd: InterstitialAd) -> Unit,
) {
    InterstitialAd.load(
        context,
        "ca-app-pub-3940256099942544/8691691433",
        AdRequest.Builder().build(),
        object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(loadedAd: InterstitialAd) {
                super.onAdLoaded(loadedAd)
                onLoaded(loadedAd)
            }
        },
    )
}

/*
// DETTE SKAL INN I SEQUEENS SCREEN
// Start loading an interstitial fullscreen ad. Only if this ad is loaded
// by the time the user presses the return arrow, will the ad be shown.
val interstitialAd = remember { mutableStateOf<InterstitialAd?>(null) }
if (AdSettings.get().displayInterstitialAdFromEmojiDetailScreen) {
    LaunchedEffect(LocalLifecycleOwner.current) {
        loadInterstitialVideoAd(context) {
            interstitialAd.value = it
        }
    }
}

 PÅ SELVE COPY KNAPPEN
{
    interstitialAd.value?.show(context as Activity)
}*/
