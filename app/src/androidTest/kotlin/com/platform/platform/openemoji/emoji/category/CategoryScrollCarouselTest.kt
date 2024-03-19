package com.platform.platform.openemoji.emoji.category

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.platform.openemoji.emoji.category.CategoryScrollCarousel
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class CategoryScrollCarouselTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSwitchingCategory() {
        // Given
        val categories = listOf("Category1", "Category2", "Category3")
        var selectedCategory: String? = null
        val onSelectCategory: (String) -> Unit = { category ->
            selectedCategory = category
        }
        composeTestRule.setContent {
            CategoryScrollCarousel(
                selectedCategory = "Category1",
                categories = categories,
                onSelectCategory = onSelectCategory,
            )
        }

        // When
        composeTestRule.onNodeWithText("Category2").performClick()

        // Then
        assertEquals("Category2", selectedCategory)
    }
}
