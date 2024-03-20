package com.platform.openemoji.emoji

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class EmojiRepository(private val context: Context) {
    private val emojisCache: ConcurrentMap<String, Emoji> = ConcurrentHashMap()

    // This is only needed when not using an API
    private var mockdata: List<Emoji>? = null

    // This method loads the emojis from the assets folder and is only needed when not using an API
    private suspend fun loadMockdata(simulatedDelay: Long = 0): List<Emoji> {
        // Simulate a delay to show the loading state
        delay(simulatedDelay)

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
        val allEmojis = loadMockdata(1000)
        return allEmojis.map { it.category }.distinct()
    }

    suspend fun getEmojisFromCategory(
        category: String,
        limit: Int = Int.MAX_VALUE,
    ): List<Emoji> {
        val cachedEmojis = emojisCache.values.filter { it.category == category }

        if (cachedEmojis.size >= limit) {
            return cachedEmojis.take(limit)
        }

        val allEmojis = loadMockdata(1000)
        val remainingEmojis =
            allEmojis.filter {
                it.category == category && it !in cachedEmojis
            }

        for (emoji in remainingEmojis) {
            emojisCache[emoji.name] = emoji
        }

        return (cachedEmojis + remainingEmojis).take(limit)
    }

    suspend fun getOverviewEmojis(
        categories: List<String>,
        limit: Int,
    ): Map<String, List<Emoji>> {
        val overviewEmojis = mutableMapOf<String, List<Emoji>>()

        for (category in categories) {
            val emojisForCategory = getEmojisFromCategory(category, limit)
            overviewEmojis[category] = emojisForCategory
        }

        return overviewEmojis
    }

    suspend fun getEmoji(name: String): Emoji {
        val cachedEmoji = emojisCache[name]
        if (cachedEmoji != null) return cachedEmoji

        val allEmojis = loadMockdata(1000)
        val emoji =
            allEmojis.find { it.name == name }
                ?: throw IllegalArgumentException("Emoji not found")

        emojisCache[emoji.name] = emoji

        return emoji
    }

    suspend fun searchEmojis(query: String): List<Emoji> {
        val cachedEmojis =
            emojisCache.values.filter {
                it.name.contains(query, ignoreCase = true)
            }

        if (cachedEmojis.isNotEmpty()) {
            return cachedEmojis
        }

        val allEmojis = loadMockdata(1000)
        val matchingEmojis =
            allEmojis.filter {
                it.name.contains(
                    query,
                    ignoreCase = true,
                )
            }

        for (emoji in matchingEmojis) {
            emojisCache[emoji.name] = emoji
        }

        return matchingEmojis
    }
}
