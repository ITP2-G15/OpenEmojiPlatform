package com.platform.openemoji.ads

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

/**
 * Interstitial ads are fullscreen ads displayed at natural transition points in the app.
 * Loads an interstitial ad and calls onLoaded with the
 * loaded ad as the argument - as long as the ad was loaded successfully.
 * User can only get interstitial every 2 minutes
 *
 * @param context can be accessed in a composable using "LocalContext.current".
 * @param onLoaded the function called with the ad as argument when the ad is finished loading.
 */

object InterstitialAds {
    private const val COOLDOWN: Long = 2 * 60 * 1000 // 2 minutes
    private var lastLoadTime: Long = 0

    fun load(
        context: Context,
        onLoaded: (loadedAd: InterstitialAd) -> Unit,
    ) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastLoadTime >= COOLDOWN) {
            InterstitialAd.load(
                context,
                "ca-app-pub-3940256099942544/1033173712",
                AdRequest.Builder().build(),
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(loadedAd: InterstitialAd) {
                        super.onAdLoaded(loadedAd)
                        onLoaded(loadedAd)
                        lastLoadTime = System.currentTimeMillis()
                    }
                },
            )
        }
    }
}
