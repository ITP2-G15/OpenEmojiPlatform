package com.platform.openemoji.screens

import android.content.ClipboardManager
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.platform.openemoji.favorites.Favorite
import com.platform.openemoji.favorites.FavoritesDataRepository
import com.platform.openemoji.favorites.FavoritesRepository
import com.platform.openemoji.favorites.FavoritesViewModel
import com.platform.openemoji.theme.OpenEmojiPlatformTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoritesScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val copyTestRule = createAndroidComposeRule<ComponentActivity>()

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

        composeTestRule.setContent {
            FavoritesScreen(favoritesViewModel = testFavoritesViewModel)
        }
        copyTestRule.setContent {
            OpenEmojiPlatformTheme {
                FavoritesScreen(favoritesViewModel = testFavoritesViewModel)
            }
        }
    }

    @Test
    fun testFavoritesScreenUi() {
        // test that the favorites screen is displayed properly
        composeTestRule.onNodeWithTag("favoritesScreen").assertExists()
        composeTestRule.onNodeWithTag("emojiSequence/aaab").assertExists()
        composeTestRule.onNodeWithTag("emojiSequence/babb").assertExists()
        composeTestRule.onAllNodesWithTag("favoriteCard").assertCountEquals(2)

        composeTestRule.onNodeWithTag("showCreateDialogButton").performClick()
    }

    @Test
    fun testFavoritesScreenFunctionality() {
        // Test copy of favorite sequence
        copyTestRule.onNodeWithTag(
            "copyButton/" + testFavorites[0].name,
        ).performClick()

        // Test that the emoji code got copied to clipboard
        val clipboardManager =
            copyTestRule.activity.applicationContext.getSystemService(
                Context.CLIPBOARD_SERVICE,
            ) as ClipboardManager
        val copiedText = clipboardManager.primaryClip?.getItemAt(0)?.text?.toString()

        // Test that the emoji code got copied to clipboard
        assert(testFavorites[0].emojiCodes.toString() == copiedText)

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
