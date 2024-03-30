package com.platform.platform.openemoji.navigation

class NavigationTest {
    /*
@get:Rule
val composeTestRule = createComposeRule()

@Test
fun testEmojiIconNavigation() {
    SearchScreenEmojiCatalogue.get().populate(
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
    composeTestRule.onNodeWithText("a").assertExists()
}

@Test
fun testNavigationStatePreservation() {
    composeTestRule.setContent {
        Navigation()
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
     */
}
