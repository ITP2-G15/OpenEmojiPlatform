package com.platform.openemoji.emoji

import android.content.ClipboardManager
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.platform.openemoji.theme.OpenEmojiPlatformTheme
import org.junit.Rule
import org.junit.Test

class EmojiIconTest {
    // An activity is needed to access the clipboard
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testIconCopy() {
        composeTestRule.setContent {
            OpenEmojiPlatformTheme {
                IconCopy(
                    emoji =
                        Emoji(
                            // This should be copied to clipboard
                            "emoji code",
                            "",
                            "a",
                            "",
                            0,
                            "",
                            "",
                            1f,
                            1f,
                        ),
                )
            }
        }

        composeTestRule.onNodeWithTag("emojiIconCopyButton")
            .performClick()

        // Jesus christ
        val clipboardManager =
            composeTestRule.activity.applicationContext.getSystemService(
                Context.CLIPBOARD_SERVICE,
            ) as ClipboardManager
        val copiedText = clipboardManager.primaryClip?.getItemAt(0)?.text?.toString()

        // Test that the emoji code got copied to clipboard
        assert("emoji code" == copiedText)
    }
}
