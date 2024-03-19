package com.platform.openemoji.news

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class NewsRepository(private val context: Context) {
    // This is only needed when not using an API
    private var mockdata: List<News>? = null

    // This method loads the emojis from the assets folder and is only needed when not using an API
    private suspend fun loadMockdata(simulatedDelay: Long = 0): List<News> {
        // Simulate a delay to show the loading state
        delay(simulatedDelay)

        if (mockdata != null) return mockdata!!

        withContext(Dispatchers.IO) {
            val inputStream = context.assets.open("News.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charsets.UTF_8)
            mockdata = Json.decodeFromString<List<News>>(json)
        }

        return mockdata!!
    }

    suspend fun getNews(limit: Int = Int.MAX_VALUE): List<News> {
        val allNews = loadMockdata(1000)
        return allNews.take(limit)
    }
}
