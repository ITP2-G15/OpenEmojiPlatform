package com.platform.openemoji.favorites

import android.content.ClipboardManager
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.platform.openemoji.screens.FavoritesScreen
import com.platform.openemoji.theme.OpenEmojiPlatformTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoritesScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var testFavorites: MutableList<Favorite>
    private lateinit var testFavoritesViewModel: FavoritesViewModel
    private lateinit var testFavoritesRepository: FavoritesRepository

    @Before
    fun init() {
        testFavorites =
            mutableListOf(
                Favorite(
                    "a",
                    arrayOf(
                        "aa",
                        "ab",
                    ),
                ),
                Favorite(
                    "b",
                    arrayOf(
                        "ba",
                        "bb",
                    ),
                ),
            )
        testFavoritesRepository = FavoritesDataRepository(null, testFavorites)
        testFavoritesViewModel = FavoritesViewModel(testFavoritesRepository)
    }

    @Test
    fun testFavoritesScreenUi() {
        composeTestRule.setContent {
            OpenEmojiPlatformTheme {
                FavoritesScreen(favoritesViewModel = testFavoritesViewModel)
            }
        }
        // test that the favorites screen is displayed properly
        composeTestRule.onNodeWithTag("favoritesScreen").assertExists()
        composeTestRule.onNodeWithTag("emojiSequence/aaab").assertExists()
        composeTestRule.onNodeWithTag("emojiSequence/babb").assertExists()
        composeTestRule.onAllNodesWithTag("favoriteCard").assertCountEquals(2)

        composeTestRule.onNodeWithTag("showCreateDialogButton").performClick()
    }

    @Test
    fun testFavoritesScreenFunctionality() {
        composeTestRule.setContent {
            OpenEmojiPlatformTheme {
                FavoritesScreen(favoritesViewModel = testFavoritesViewModel)
            }
        }
        // Test copy of favorite sequence
        composeTestRule.onNodeWithTag(
            "copyButton/" + testFavorites[0].name,
        ).performClick()

        // Test that the emoji code got copied to clipboard
        val clipboardManager =
            composeTestRule.activity.applicationContext.getSystemService(
                Context.CLIPBOARD_SERVICE,
            ) as ClipboardManager
        val copiedText = clipboardManager.primaryClip?.getItemAt(0)?.text?.toString()
        assert(testFavorites[0].emojiCodes.joinToString("") == copiedText)

        // Test delete of favorite sequence
        composeTestRule.onNodeWithTag(
            "deleteButton/" + testFavorites[1].name,
        ).performClick()
        composeTestRule.onNodeWithTag("confirmDeleteButton").assertExists()
        composeTestRule.onNodeWithTag("confirmDeleteButton").performClick()
        composeTestRule.onNodeWithTag(
            "favoriteName" + testFavorites[1].name,
        ).assertDoesNotExist()

        // Test showCreateDialog button click
        composeTestRule.onNodeWithTag("showCreateDialogButton").performClick()
        composeTestRule.onNodeWithTag("createFavoriteDialog").assertExists()
    }
}
