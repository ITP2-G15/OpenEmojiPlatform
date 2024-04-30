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
