package com.platform.openemoji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.amplitude.android.Amplitude
import com.amplitude.android.Configuration
import com.amplitude.android.DefaultTrackingOptions
import com.amplitude.core.ServerZone
import com.google.android.gms.ads.MobileAds
import com.platform.openemoji.navigation.Navigation
import com.platform.openemoji.theme.OpenEmojiPlatformTheme

@Suppress("ktlint:standard:property-naming")
val LocalAnalytics = compositionLocalOf<Amplitude?> { null }

class Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amplitude =
            Amplitude(
                Configuration(
                    // It's safe to expose api key
                    // https://github.com/amplitude/Amplitude-Javascript/issues/100
                    apiKey = "efe7c6f94fd4d20f32b6f9b91f66d6be",
                    context = applicationContext,
                    defaultTracking = DefaultTrackingOptions.ALL,
                    serverZone = ServerZone.EU,
                    flushIntervalMillis = 10000,
                ),
            )
        amplitude.track("Hello")
        amplitude.flush()

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
