package com.platform.openemoji.news

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NewsRepositoryTest {
    private lateinit var repository: NewsMockDataRepository

    @Before
    fun setup() {
        val testNews =
            listOf(
                News(
                    "name1",
                    "url1",
                    "image1",
                    "description1",
                ),
                News(
                    "name2",
                    "url2",
                    "image2",
                    "description2",
                ),
                News(
                    "name3",
                    "url3",
                    "image3",
                    "description3",
                ),
            )
        repository = NewsMockDataRepository(null, testNews)
    }

    @Test
    fun testGetNews() =
        runBlocking {
            val news = repository.getNews()
            assertEquals(3, news.size)
        }

    @Test
    fun testGetLimitedNews() =
        runBlocking {
            val news = repository.getNews(2)
            assertEquals(2, news.size)
        }
}
