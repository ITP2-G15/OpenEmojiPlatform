package com.platform.platform.openemoji.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.navigation.Navigation
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testEmojiIconNavigation() {
        EmojiCatalogue.get().populate(
            listOf(
                Emoji(
                    "0",
                    "a",
                    "hh",
                    "A",
                    "AA",
                    "",
                    "",
                ),
            ),
        )
        composeTestRule.setContent {
            Navigation()
        }
        // Make sure we're in the search screen.
        composeTestRule.onNodeWithTag("bottomNavigationBarSearch")
            .performClick()
        // Test that clicking on the emoji icon takes you to emoji details.
        composeTestRule.onNodeWithTag("emojiDetailScreen").assertDoesNotExist()
        composeTestRule.onAllNodesWithTag("iconRoute")[0]
            .performClick()
        composeTestRule.onNodeWithTag("emojiDetailScreen").assertExists()
        // Test that it's the correct emoji details screen.
        composeTestRule.onNodeWithText("a")
    }
}
