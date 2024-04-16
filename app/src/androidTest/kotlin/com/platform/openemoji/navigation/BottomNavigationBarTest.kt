package com.platform.openemoji.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class BottomNavigationBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testNavigationBar() {
        composeTestRule.setContent {
            Navigation()
        }

        // Test that each nav bar button takes you to the right screen,
        // and that the previous screen is not displayed anymore.
        var previousScreen: String? = null
        listOf(
            "bottomNavigationBarHome" to "homeScreen",
            "bottomNavigationBarSearch" to "searchScreen",
            "bottomNavigationBarGame" to "gameScreen",
            // Extend with future screens here
        ).forEach { (navTag, screenTag) ->
            composeTestRule.onNodeWithTag(navTag)
                .performClick()
            composeTestRule.onNodeWithTag(screenTag)
                .assertIsDisplayed()
            previousScreen?.let {
                composeTestRule.onNodeWithTag(it).assertIsNotDisplayed()
            }
            previousScreen = screenTag
        }
    }
}
