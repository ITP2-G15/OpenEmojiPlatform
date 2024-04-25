@file:Suppress("ktlint:standard:no-empty-file")

package com.platform.openemoji.favorites
//
// import android.content.ClipboardManager
// import android.content.Context
// import androidx.activity.ComponentActivity
// import androidx.compose.ui.test.assertTextEquals
// import androidx.compose.ui.test.junit4.createAndroidComposeRule
// import androidx.compose.ui.test.onNodeWithTag
// import androidx.compose.ui.test.performClick
// import com.platform.openemoji.theme.OpenEmojiPlatformTheme
// import org.junit.Before
// import org.junit.Rule
// import org.junit.Test
//
// class FavoriteCardTest {
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
//
//    private lateinit var favorite: Favorite
//
//    @Before
//    fun setUp() {
//        favorite = Favorite(name = "Test", emojiSequence = "😀😃😄😁😆")
//    }
//
//    @Test
//    fun sequence_displaysCorrectName() {
//        composeTestRule.setContent {
//            Sequence(favorite = favorite)
//        }
//
//        composeTestRule.onNodeWithTag("favoriteName").assertTextEquals("Test")
//    }
//
//    @Test
//    fun sequence_hasCopyButton() {
//        composeTestRule.setContent {
//            Sequence(favorite = favorite)
//        }
//
//        composeTestRule.onNodeWithTag("copyButton").assertExists()
//    }
//
//    @Test
//    fun sequence_hasDeleteButton() {
//        composeTestRule.setContent {
//            Sequence(favorite = favorite)
//        }
//
//        composeTestRule.onNodeWithTag("deleteButton").assertExists()
//    }
//
//    @Test
//    fun sequence_copiesEmojiSequenceToClipboard() {
//        composeTestRule.setContent {
//            OpenEmojiPlatformTheme {
//                Sequence(favorite = favorite)
//            }
//        }
//
//        composeTestRule.onNodeWithTag("copyButton").performClick()
//
//        val clipboardManager =
//            composeTestRule.activity.applicationContext.getSystemService(
//                Context.CLIPBOARD_SERVICE,
//            ) as ClipboardManager
//        val copiedText = clipboardManager.primaryClip?.getItemAt(0)?.text?.toString()
//
//        assert(favorite.emojiSequence == copiedText)
//    }
// }
