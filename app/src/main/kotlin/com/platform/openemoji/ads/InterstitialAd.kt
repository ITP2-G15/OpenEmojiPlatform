package com.platform.openemoji.ads

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

object InterstitialAd {
    private const val ADUNITID = "ca-app-pub-3940256099942544/1033173712"
    private var interstitialAd: InterstitialAd? = null
    private const val COOLDOWN: Long = 2 * 60 * 1000 // 2 minutes
    private var lastShownTime: Long = 0

    fun show(context: Context) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastShownTime >= COOLDOWN) {
            interstitialAd?.let {
                it.show(context as Activity)
                lastShownTime = System.currentTimeMillis()
                interstitialAd = null
            }
        }
    }

    fun load(context: Context) {
        if (interstitialAd != null) return
        InterstitialAd.load(
            context,
            ADUNITID,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(loadedAd: InterstitialAd) {
                    super.onAdLoaded(loadedAd)
                    interstitialAd = loadedAd
                }
            },
        )
    }
}
