package com.platform.openemoji.ad

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import com.platform.openemoji.ads.TopBottomAd
import org.junit.Rule
import org.junit.Test

class TopBottomAdTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testTopBottomAdDisplaysWithCorrectHeight() {
        composeTestRule.setContent {
            TopBottomAd(minHeight = 60.dp)
        }
        composeTestRule.onNodeWithTag(
            "TopBottomAdView",
        ).assertIsDisplayed().assertHeightIsAtLeast(60.dp)
    }
}
