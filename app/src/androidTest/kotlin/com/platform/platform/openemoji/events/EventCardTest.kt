package com.platform.platform.openemoji.events

import androidx.compose.ui.test.junit4.createComposeRule
import com.platform.openemoji.events.Event
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EventCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        val event =
            Event(
                "St Patrick's day",
                "17.03",
                "https://emojipedia.org/_next/image?url=https%3A%2F%2Fem-content." +
                    "zobj.net%2Fcontent%2Fevents%2FEarth_Day_PNG.png&w=1500&q=75",
                "https://emojipedia.org/st-patricks-day",
            )
    }

    @Test
    fun testEventCard() {
    }
}
