package com.platform.openemoji.emoji.catalogue

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.platform.openemoji.emoji.Emoji
import org.junit.Rule
import org.junit.Test

class EmojiCatalogueTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testEmojiCatalogueStructure() {
        val navController =
            TestNavHostController(ApplicationProvider.getApplicationContext())
        val emojisByCategory =
            listOf(
                Emoji("1", "a", "", "", 0, "A", "", 1f, 1f),
                Emoji("2", "b", "", "", 0, "A", "", 1f, 1f),
                Emoji("3", "c", "", "", 0, "A", "", 1f, 1f),
                Emoji("4", "d", "", "", 0, "B", "", 1f, 1f),
            ).groupBy { it.category }
        composeTestRule.setContent {
            EmojiCatalogue(
                emojisByCategory,
                navController,
            )
        }

        // Test that there are 4 emoji icons on the screen.
        composeTestRule.onAllNodesWithTag("iconRoute")
            .assertCountEquals(4)
        // Test that there are 2 emoji categories: A and B.
        composeTestRule.onAllNodesWithTag("catalogueCategoryHeader")
            .assertCountEquals(2)
        composeTestRule.onNodeWithText("A").assertExists()
        composeTestRule.onNodeWithText("B").assertExists()
    }
}
