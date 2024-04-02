package com.platform.openemoji.news

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface NewsRepository {
    suspend fun getNews(limit: Int = Int.MAX_VALUE): List<News> = emptyList()
}

class NewsMockDataRepository(
    private val context: Context?,
    private val testNews: List<News>? = null,
    private val simulatedDelay: Long = 0,
) : NewsRepository {
    // This is only needed when not using an API
    private var mockdata: List<News>? = null

    // This method loads the emojis from the assets folder and is only needed when not using an API
    private suspend fun loadMockdata(): List<News> {
        if (mockdata != null) return mockdata!!

        // Simulate a delay to show the loading state
        delay(simulatedDelay)

        mockdata =
            if (context != null) {
                withContext(Dispatchers.IO) {
                    val inputStream = context.assets.open("news.json")
                    val size = inputStream.available()
                    val buffer = ByteArray(size)
                    inputStream.read(buffer)
                    inputStream.close()
                    val json = String(buffer, Charsets.UTF_8)
                    Json.decodeFromString<List<News>>(json)
                }
            } else {
                testNews ?: emptyList()
            }

        return mockdata!!
    }

    override suspend fun getNews(limit: Int): List<News> {
        val allNews = loadMockdata()
        return allNews.take(limit)
    }
}
