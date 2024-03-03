package com.platform.platform.openemoji.emoji.catalogue

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueUi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmojiCatalogueUiTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val emojiCatalogue = EmojiCatalogue.get()

    @Before
    fun init() {
        emojiCatalogue.populate(
            listOf(
                Emoji("1", "a", "", "A", "AA", "", ""),
                Emoji("2", "b", "", "A", "AA", "", ""),
                Emoji("3", "c", "", "A", "AB", "", ""),
                Emoji("4", "d", "", "B", "BA", "", ""),
            ),
        )
    }

    @Test
    fun testEmojiCatalogueStructure() {
        composeTestRule.setContent {
            EmojiCatalogueUi(
                emojis = emojiCatalogue.byCategory,
            )
        }

        // Test that there are 4 emoji icons on the screen.
        composeTestRule.onAllNodesWithTag("iconRoute")
            .assertCountEquals(4)
        // Test that there are 2 emoji categories: A and B.
        composeTestRule.onAllNodesWithTag("catalogueUiCategoryHeader")
            .assertCountEquals(2)
        composeTestRule.onNodeWithText("A").assertExists()
        composeTestRule.onNodeWithText("B").assertExists()
    }
}
