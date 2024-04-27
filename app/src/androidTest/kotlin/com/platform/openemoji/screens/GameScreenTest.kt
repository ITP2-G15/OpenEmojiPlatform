package com.platform.openemoji.screens

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.platform.openemoji.game.GameMockDataRepository
import com.platform.openemoji.game.GameViewModel
import com.platform.openemoji.game.Level
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var testLevels: List<Level>
    private lateinit var testGameViewModel: GameViewModel
    private lateinit var testRepository: GameMockDataRepository

    @Before
    fun init() {
        testLevels =
            listOf(
                Level(
                    "🤓🤓",
                    listOf("Nerd", "Andreas Li"),
                    0,
                ),
                Level(
                    "🐐🐐🐐🐐",
                    listOf("Ariana Grande", "Taylor Swift", "Madison Beer", "Bae Suzy"),
                    3,
                ),
            )
        testRepository = GameMockDataRepository(null, testLevels)
        testGameViewModel = GameViewModel(testRepository)

        composeTestRule.setContent {
            GameScreen(gameViewModel = testGameViewModel)
        }
    }

    @Test
    fun testGameScreenUi() {
        // test that GameQuestionCard is displayed properly
        composeTestRule.onNodeWithTag("GameQuestionCard").assertExists()
        composeTestRule.onNodeWithText("🤓🤓").assertExists()
        composeTestRule.onNodeWithText("Level 1").assertExists()

        // test that GameAlternativeButtons is displayed properly
        composeTestRule.onAllNodesWithTag("GameAlternativeButton")
            .assertCountEquals(2)
    }

    @Test
    fun testGameScreenFunctionality() {
        // test that clicking on wrong and right alternatives works as intended
        composeTestRule.onNodeWithText("Andreas Li")
            .performClick()
        composeTestRule.onNodeWithText("Level 1").assertExists()

        composeTestRule.onNodeWithText("Nerd")
            .performClick()
        composeTestRule.onNodeWithText("Level 2").assertExists()

        composeTestRule.onNodeWithText("Bae Suzy")
            .performClick()
        // test that the right string is shown if the user beats all levels
        composeTestRule.onNodeWithText(
            "Congratulations you have beat all levels!",
        ).assertExists()
    }
}
