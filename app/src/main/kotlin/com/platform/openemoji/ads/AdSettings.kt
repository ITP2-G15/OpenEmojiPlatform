package com.platform.openemoji.ads

// Intended to be used for testing.
class AdSettings private constructor() {
    // Singleton instance provider
    companion object {
        @Volatile
        private var instance: AdSettings? = null

        fun get() =
            instance ?: synchronized(this) {
                instance ?: AdSettings().also { instance = it }
            }
    }

    var displayInterstitialAdFromEmojiDetailScreen = true
}
