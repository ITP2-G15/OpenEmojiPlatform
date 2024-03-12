package com.platform.openemoji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.platform.openemoji.emoji.EmojiMockData
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.navigation.Navigation
import com.platform.openemoji.theme.OpenEmojiPlatformTheme

class EmojiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Loads all emojis once for use by all the components that need them.
        val emojiMockData = EmojiMockData.getFrom(resources)
        EmojiCatalogue.get().populate(emojiMockData)
        setContent {
            OpenEmojiPlatformTheme {
                Navigation()
            }
        }
    }
}
