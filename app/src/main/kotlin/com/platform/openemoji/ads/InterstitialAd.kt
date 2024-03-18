package com.platform.openemoji.ads

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

/**
 * Interstitial ads are fullscreen ads displayed at natural transition points in the app.
 * Loads an interstitial ad and calls onLoaded with the
 * loaded ad as the argument - as long as the ad was loaded successfully.
 *
 * @param context can be accessed in a composable using "LocalContext.current".
 * @param onLoaded the function called with the ad as argument when the ad is finished loading.
 */
fun loadInterstitialAd(
    context: Context,
    onLoaded: (loadedAd: InterstitialAd) -> Unit,
) {
    InterstitialAd.load(
        context,
        "ca-app-pub-3940256099942544/1033173712",
        AdRequest.Builder().build(),
        object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(loadedAd: InterstitialAd) {
                super.onAdLoaded(loadedAd)
                onLoaded(loadedAd)
            }
        },
    )
}
