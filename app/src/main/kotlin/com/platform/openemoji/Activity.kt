package com.platform.openemoji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.amplitude.android.Amplitude
import com.google.android.gms.ads.MobileAds
import com.platform.openemoji.navigation.Navigation
import com.platform.openemoji.theme.OpenEmojiPlatformTheme

@Suppress("ktlint:standard:property-naming")
val LocalAnalytics = compositionLocalOf<Amplitude?> { null }

class Activity : ComponentActivity() {
    private var amplitude = Application.amplitude

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this) {}
        setContent {
            CompositionLocalProvider(LocalAnalytics provides amplitude) {
                OpenEmojiPlatformTheme {
                    Navigation()
                }
            }
        }
    }
}
