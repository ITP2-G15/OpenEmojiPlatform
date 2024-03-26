package com.platform.openemoji.emoji

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class EmojiRepository(private val context: Context) {
    private val simulatedDelay: Long = 1000
    private val emojisByCategory: ConcurrentMap<String, List<Emoji>> = ConcurrentHashMap()

    private var mockdata: List<Emoji>? = null

    // This method loads the emojis from the assets folder and is only needed when not using an API
    private suspend fun loadMockdata(): List<Emoji> {
        if (mockdata != null) return mockdata!!

        withContext(Dispatchers.IO) {
            val inputStream = context.assets.open("emojis.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charsets.UTF_8)
            mockdata = Json.decodeFromString<List<Emoji>>(json)
        }

        return mockdata!!
    }

    suspend fun getCategories(): List<String> {
        delay(simulatedDelay)
        val allEmojis = loadMockdata()
        return allEmojis.map { it.category }.distinct()
    }

    suspend fun getEmojisOfCategory(category: String): List<Emoji> {
        var emojisOfCategory = emojisByCategory[category]
        if (emojisOfCategory != null) {
            return emojisOfCategory
        }

        delay(simulatedDelay)
        val allEmojis = loadMockdata()
        emojisOfCategory = allEmojis.filter { it.category == category }
        emojisByCategory[category] = emojisOfCategory
        return emojisOfCategory
    }

    suspend fun getOverviewEmojis(limitPerCategory: Int): Map<String, List<Emoji>> {
        delay(simulatedDelay)
        return loadMockdata()
            .groupBy { it.category }
            .map { (category, emojis) ->
                category to emojis.take(limitPerCategory)
            }
            .toMap()
    }

    suspend fun getPopularEmojis(limit: Int = Int.MAX_VALUE): List<Emoji> {
        delay(simulatedDelay)
        val allEmojis = loadMockdata()
        return allEmojis.sortedByDescending { it.popularity }.take(limit)
    }

    suspend fun getEmoji(name: String): Emoji? {
        delay(simulatedDelay)
        return loadMockdata().find { it.name == name }
    }

    suspend fun searchEmojis(query: String): List<Emoji> {
        delay(simulatedDelay)

        return loadMockdata().filter {
            it.name.contains(
                query,
                ignoreCase = true,
            )
        }
    }
}
