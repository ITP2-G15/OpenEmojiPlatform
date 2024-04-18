package com.platform.openemoji.ad

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import com.platform.openemoji.ads.InlineAd
import org.junit.Rule
import org.junit.Test

class InlineAdTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testInlineAdDisplaysWithCorrectHeight() {
        composeTestRule.setContent {
            InlineAd(minHeight = 100.dp)
        }
        composeTestRule.onNodeWithTag(
            "InlineAdView",
        ).assertIsDisplayed().assertHeightIsAtLeast(100.dp)
    }
}
