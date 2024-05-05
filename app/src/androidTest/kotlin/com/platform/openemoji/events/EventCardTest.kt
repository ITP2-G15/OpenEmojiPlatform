package com.platform.openemoji.events

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EventCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var event: Event

    @Before
    fun init() {
        event =
            Event(
                "St Patrick's day",
                "17.03",
                "https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_1280.jpg",
                "https://www.google.com/search?q=st-patricks-day",
            )
    }

    @Test
    fun testEventCard() {
        composeTestRule.setContent {
            EventCard(event)
        }
        // Test that the eventCard is displayed.
        composeTestRule.onNodeWithTag("eventCard").assertExists()

        // Test that the eventCard contains the correct event name.
        composeTestRule.onNodeWithText("St Patrick's day").assertExists()

        // Test that the eventCard contains the correct event date.
        composeTestRule.onNodeWithText("17.03").assertExists()

        // Test that the eventCard contains the correct event image.
        composeTestRule.onNodeWithContentDescription(
            "St Patrick's day",
        ).assertExists()
    }

    @Test
    fun testEventCardNavigateToRemoteURL() {
        Intents.init()
        composeTestRule.setContent {
            EventCard(event)
        }

        // Test that clicking on the eventCard takes you to the event web page.
        composeTestRule.onNodeWithTag("eventCard")
            .performClick()

        Intents.intended(IntentMatchers.hasAction(Intent.ACTION_VIEW))
        Intents.intended(IntentMatchers.hasData(Uri.parse(event.url)))
        Intents.release()
    }
}
