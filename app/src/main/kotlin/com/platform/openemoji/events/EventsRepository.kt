package com.platform.openemoji.events

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface EventsRepository {
    suspend fun getEvents(limit: Int = Int.MAX_VALUE): List<Event> = emptyList()
}

class EventsMockDataRepository(
    private val context: Context?,
    private val testEvents: List<Event>? = null,
    private val simulatedDelay: Long = 0,
) : EventsRepository {
    // This is only needed when not using an API
    private var mockdata: List<Event>? = null

    // This method loads the emojis from the assets folder and is only needed when not using an API
    private suspend fun loadMockdata(): List<Event> {
        if (mockdata != null) return mockdata!!

        // Simulate a delay to show the loading state
        delay(simulatedDelay)

        mockdata =
            if (context != null) {
                withContext(Dispatchers.IO) {
                    val inputStream = context.assets.open("events.json")
                    val size = inputStream.available()
                    val buffer = ByteArray(size)
                    inputStream.read(buffer)
                    inputStream.close()
                    val json = String(buffer, Charsets.UTF_8)
                    Json.decodeFromString<List<Event>>(json)
                }
            } else {
                testEvents ?: emptyList()
            }

        return mockdata!!
    }

    override suspend fun getEvents(limit: Int): List<Event> {
        val allEvents = loadMockdata()
        return allEvents.take(limit)
    }
}
