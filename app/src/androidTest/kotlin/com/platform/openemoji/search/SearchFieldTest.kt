package com.platform.openemoji.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SearchFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSearchField() {
        composeTestRule.setContent {
            val searchQuery =
                remember {
                    mutableStateOf("")
                }
            SearchField(searchQuery.value) { searchQuery.value = it }
        }

        composeTestRule.onNodeWithTag("searchTextField")
            .performTextInput("hello")

        composeTestRule.onNodeWithText("hello").assertExists()
    }
}
