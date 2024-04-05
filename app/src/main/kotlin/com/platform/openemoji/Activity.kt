package com.platform.openemoji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.amplitude.android.Amplitude
import com.amplitude.android.Configuration
import com.amplitude.android.DefaultTrackingOptions
import com.amplitude.core.ServerZone
import com.google.android.gms.ads.MobileAds
import com.platform.openemoji.navigation.Navigation
import com.platform.openemoji.theme.OpenEmojiPlatformTheme

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
                    flushIntervalMillis = 2000,
                ),
            )

        MobileAds.initialize(this) {}
        setContent {
            OpenEmojiPlatformTheme {
                Navigation()
            }
        }
    }
}
