package com.platform.openemoji.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.platform.openemoji.RepositoryStore
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.EmojiRepository
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testEmojiIconNavigation() {
        // Put an emoji in home screen to test navigation to detail screen.
        val testEmoji =
            Emoji(
                "a",
                "a",
                "a",
                "name",
                1,
                "",
                "a",
                0f,
                0f,
            )

        val testEmojiRepository =
            object : EmojiRepository {
                // We're going to click on the single emoji in home screen's most popular section
                override suspend fun getPopularEmojis(limit: Int): List<Emoji> =
                    listOf(testEmoji)

                // Used to switch to emoji detail screen
                override suspend fun getEmoji(name: String) = testEmoji
            }

        composeTestRule.setContent {
            Navigation(
                object : RepositoryStore {
                    override val emojiRepository: EmojiRepository by lazy {
                        testEmojiRepository
                    }
                },
            )
        }
        // Make sure we're in the home screen.
        composeTestRule.onNodeWithTag("bottomNavigationBarHome")
            .performClick()
        // Test that clicking on the emoji icon takes you to emoji details.
        composeTestRule.onNodeWithTag("emojiDetailScreen").assertDoesNotExist()
        composeTestRule.onAllNodesWithTag("iconRoute")[0]
            .performClick()
        composeTestRule.onNodeWithTag("emojiDetailScreen").assertExists()
        // Test that it's the correct emoji details screen.
        composeTestRule.onNodeWithText("name").assertExists()
    }

    @Test
    fun testNavigationStatePreservation() {
        composeTestRule.setContent {
            Navigation(object : RepositoryStore {})
        }

        // Make sure we're in the search screen.
        composeTestRule.onNodeWithTag("bottomNavigationBarSearch")
            .performClick()

        // Perform search to bring the screen into a state we can test the preservation of.
        composeTestRule.onNodeWithTag("searchTextField")
            .performTextInput("h")
        composeTestRule.onNodeWithText("Search results for: h").assertExists()

        // Go to home screen and back, and test that it's still in a search state.
        composeTestRule.onNodeWithTag("bottomNavigationBarHome")
            .performClick()
        composeTestRule.onNodeWithTag("bottomNavigationBarSearch")
            .performClick()
        composeTestRule.onNodeWithText("Search results for: h").assertExists()
    }
}
