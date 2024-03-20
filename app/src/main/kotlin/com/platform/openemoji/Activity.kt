package com.platform.openemoji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.gms.ads.MobileAds
import com.platform.openemoji.navigation.Navigation
import com.platform.openemoji.theme.OpenEmojiPlatformTheme

class Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this) {}
        setContent {
            OpenEmojiPlatformTheme {
                Navigation()
            }
        }
    }
}
