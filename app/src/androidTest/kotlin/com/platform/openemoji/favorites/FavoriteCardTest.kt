package com.platform.openemoji.favorites

/*
class FavoriteCardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var favorite: Favorite

    @Before
    fun setUp() {
        favorite = Favorite(name = "Test", emojiSequence = "😀😃😄😁😆")
    }

    @Test
    fun sequence_displaysCorrectName() {
        composeTestRule.setContent {
            Sequence(favorite = favorite)
        }

        composeTestRule.onNodeWithTag("favoriteName").assertTextEquals("Test")
    }

    @Test
    fun sequence_hasCopyButton() {
        composeTestRule.setContent {
            Sequence(favorite = favorite)
        }

        composeTestRule.onNodeWithTag("copyButton").assertExists()
    }

    @Test
    fun sequence_hasDeleteButton() {
        composeTestRule.setContent {
            Sequence(favorite = favorite)
        }

        composeTestRule.onNodeWithTag("deleteButton").assertExists()
    }

    @Test
    fun sequence_copiesEmojiSequenceToClipboard() {
        composeTestRule.setContent {
            OpenEmojiPlatformTheme {
                Sequence(favorite = favorite)
            }
        }

        composeTestRule.onNodeWithTag("copyButton").performClick()

        val clipboardManager =
            composeTestRule.activity.applicationContext.getSystemService(
                Context.CLIPBOARD_SERVICE,
            ) as ClipboardManager
        val copiedText = clipboardManager.primaryClip?.getItemAt(0)?.text?.toString()

        assert(favorite.emojiSequence == copiedText)
    }
}
*/
