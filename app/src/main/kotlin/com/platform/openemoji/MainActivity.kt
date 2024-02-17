package com.platform.openemoji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import com.platform.openemoji.emoji.EmojiMockData
import com.platform.openemoji.ui.theme.OpenEmojiPlatformTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenEmojiPlatformTheme {
                Column {
                    Text(
                        text =
                            EmojiMockData.getFrom(
                                resources,
                            ).joinToString("\n") { "${it.title} ${it.emojiCode}" },
                    )
                }
            }
        }
    }
}
