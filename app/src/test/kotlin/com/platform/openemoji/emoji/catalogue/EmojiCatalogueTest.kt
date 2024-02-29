package com.platform.openemoji

import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EmojiCatalogueTest {
    private lateinit var emojiCatalogue: EmojiCatalogue
    private val emoji1 =
        Emoji(
            "69",
            "title1",
            "\uD83D\uDE01",
            "category1",
            "subCategory1",
            "description1",
            "imageUrl1",
        )
    private val emoji2 =
        Emoji(
            "420",
            "title2",
            "\uD83D\uDE02",
            "category1",
            "subCategory2",
            "description2",
            "imageUrl2",
        )
    private val emoji3 =
        Emoji(
            "22",
            "title3",
            "\uD83D\uDE03",
            "category2",
            "subCategory3",
            "description3",
            "imageUrl3",
        )

    @Before
    fun setup() {
        emojiCatalogue = EmojiCatalogue.get()
        emojiCatalogue.populate(listOf(emoji1, emoji2, emoji3))
    }

    @Test
    fun testPopulate() {
        val expected =
            mapOf(
                "category1" to listOf(emoji1, emoji2),
                "category2" to listOf(emoji3),
            )
        assertEquals(expected, emojiCatalogue.byCategory)
    }

    @Test
    fun testCategories() {
        val expected = listOf("category1", "category2")
        assertEquals(expected, emojiCatalogue.categories)
    }

    @Test
    fun testBySubCategory() {
        val expected =
            mapOf(
                "subCategory1" to listOf(emoji1),
                "subCategory2" to listOf(emoji2),
            )
        assertEquals(expected, emojiCatalogue.bySubCategory("category1"))

        val expected2 =
            mapOf(
                "subCategory3" to listOf(emoji3),
            )
        assertEquals(expected2, emojiCatalogue.bySubCategory("category2"))
    }
}
