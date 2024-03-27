package com.platform.platform.openemoji.search

class SearchFieldTest {
    /*
    @get:Rule
    val composeTestRule = createComposeRule()

    private val emojiCatalogue = SearchScreenEmojiCatalogue.get()

    @Before
    fun init() {
        emojiCatalogue.populate(
            listOf(
                Emoji("1", "aaa", "\uD83D\uDE00", "A", "AA", "", ""),
                Emoji("2", "abc", "\uD83D\uDE02", "A", "AA", "", ""),
                Emoji("3", "bbb", "\uD83D\uDC4D", "A", "AB", "", ""),
                Emoji("4", "ccc", "\uD83D\uDD25", "B", "BA", "", ""),
            ),
        )
    }

    @Test
    fun testSearch() {
        val navController =
            TestNavHostController(ApplicationProvider.getApplicationContext())

        composeTestRule.setContent {
            SearchScreen(navController)
        }

        // Test that there are 4 emoji icons on the screen before search
        composeTestRule.onAllNodesWithTag("iconRoute").assertCountEquals(4)

        // Perform search
        val query = "A"
        composeTestRule.onNodeWithTag("searchTextField").performTextInput(query)

        // test that search header exists
        composeTestRule.onNodeWithText("Search results for: $query").assertExists()

        // Test that there are 2 emoji icons on the screen
        composeTestRule.onAllNodesWithTag("iconRoute").assertCountEquals(2)

        // Test that there are 4 emoji icons on the screen after clearing input
        composeTestRule.onNodeWithTag("searchTextField").performTextClearance()
        composeTestRule.onAllNodesWithTag("iconRoute").assertCountEquals(4)
    }
     */
}
