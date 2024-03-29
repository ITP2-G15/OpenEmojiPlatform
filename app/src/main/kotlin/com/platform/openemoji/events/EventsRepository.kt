package com.platform.openemoji.events

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class EventsRepository(private val context: Context) {
    // This is only needed when not using an API
    private var mockdata: List<Event>? = null

    // This method loads the emojis from the assets folder and is only needed when not using an API
    private suspend fun loadMockdata(simulatedDelay: Long = 0): List<Event> {
        if (mockdata != null) return mockdata!!

        // Simulate a delay to show the loading state
        delay(simulatedDelay)

        withContext(Dispatchers.IO) {
            val inputStream = context.assets.open("events.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charsets.UTF_8)
            mockdata = Json.decodeFromString<List<Event>>(json)
        }

        return mockdata!!
    }

    suspend fun getEvents(limit: Int = Int.MAX_VALUE): List<Event> {
        val allNews = loadMockdata(1000)
        return allNews.take(limit)
    }
}
