package com.platform.openemoji.emoji

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class EmojiRepositoryTest {
    private lateinit var repository: EmojiRepository

    @Before
    fun setup() {
        val testEmojis =
            listOf(
                Emoji(
                    "code1", "unicodeCodePoints1", "url1", "name1",
                    69, "category1", "description1", 1.0f, 1.0f,
                ),
                Emoji(
                    "code2", "unicodeCodePoints2", "url2", "name2",
                    420, "category1", "description2", 2.0f, 2.0f,
                ),
                Emoji(
                    "code3", "unicodeCodePoints3", "url3", "name3",
                    20, "category2", "description3", 3.0f, 3.0f,
                ),
            )
        repository = EmojiRepository(null, testEmojis)
    }

    @Test
    fun testGetCategories() =
        runBlocking {
            val categories = repository.getCategories()
            assertEquals(listOf("category1", "category2"), categories)
        }

    @Test
    fun testGetEmojisOfCategory() =
        runBlocking {
            val categoryEmojis = repository.getEmojisOfCategory("category1")
            assertEquals(2, categoryEmojis["category1"]?.size)
        }

    @Test
    fun testGetOverviewEmojis() =
        runBlocking {
            val overviewEmojis = repository.getOverviewEmojis()
            assertEquals(2, overviewEmojis.size)
        }

    @Test
    fun testGetPopularEmojis() =
        runBlocking {
            val popularEmojis = repository.getPopularEmojis(2)
            assertEquals(2, popularEmojis.size)
        }

    @Test
    fun testGetEmoji() =
        runBlocking {
            val emoji = repository.getEmoji("name1")
            assertNotNull(emoji)
        }

    @Test
    fun testSearchEmojis() =
        runBlocking {
            val searchEmojis = repository.searchEmojis("name")
            assertEquals(3, searchEmojis.size)
        }
}
