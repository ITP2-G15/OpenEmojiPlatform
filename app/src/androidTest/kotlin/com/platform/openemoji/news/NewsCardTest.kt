package com.platform.openemoji.news

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var news: News

    @Before
    fun init() {
        news =
            News(
                "New emojis",
                "https://www.google.com/search?q=first-look-new-emojis-in-ios-17-4",
                "https://cdn.pixabay.com/photo/2017/02/20/18/03/cat-2083492_1280.jpg",
                "We are getting new emojis!!",
            )
    }

    @Test
    fun testNewsCard() {
        composeTestRule.setContent {
            NewsCard(news)
        }
        // test that newsCard is displayed properly
        composeTestRule.onNodeWithTag("newsCard").assertExists()
        composeTestRule.onNodeWithText("New emojis").assertExists()
        composeTestRule.onNodeWithText("We are getting new emojis!!").assertExists()
    }

    @Test
    fun testNewsCardNavigateToRemoteURL() {
        Intents.init()
        composeTestRule.setContent {
            NewsCard(news)
        }

        // Test that clicking on the NewsCard takes you to the event web page.
        composeTestRule.onNodeWithTag("newsCard")
            .performClick()

        Intents.intended(IntentMatchers.hasAction(Intent.ACTION_VIEW))
        Intents.intended(IntentMatchers.hasData(Uri.parse(news.url)))
        Intents.release()
    }
}
