package com.platform.openemoji.emoji

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class EmojiRepository(private val context: Context) {
    private val emojisCache = ConcurrentHashMap<String, Emoji>()

    // This is only needed when not using an API
    private var mockdata: List<Emoji>? = null

    // This method loads the emojis from the assets folder and is only needed when not using an API
    private suspend fun loadMockdata(simulatedDelay: Long? = null): List<Emoji> {
        // Simulate a delay to show the loading state
        delay(simulatedDelay ?: 0)

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

    suspend fun getEmojisByCategory(
        category: String,
        limit: Int? = null,
    ): List<Emoji>? {
        val cachedEmojis = emojisCache.values.filter { it.category == category }
        if (cachedEmojis.size >= limit ?: Int.MAX_VALUE) {
            return cachedEmojis.take(limit)
        }

        val remainingLimit = limit?.minus(cachedEmojis.size)

        // This will be the remaining emojis that need to be obtained through the API
        val allEmojis = loadMockdata(1000)
        val lastCachedEmojiIndex = allEmojis.indexOfLast { it in cachedEmojis }
        val remainingEmojis =
            allEmojis.drop(lastCachedEmojiIndex + 1)
                .filter { it.category == category }
                .take(remainingLimit ?: Int.MAX_VALUE)

        val resultEmojis = cachedEmojis + remainingEmojis

        // Only add emojis to the cache that are not already in it
        for (emoji in remainingEmojis) {
            if (emoji.name !in emojisCache.keys) {
                emojisCache[emoji.name] = emoji
            }
        }

        return resultEmojis
    }

    suspend fun getEmojiBySearch(search: String): List<Emoji>? {
        val cachedEmojis =
            emojisCache.values.filter {
                it.name.contains(search, ignoreCase = true)
            }

        if (cachedEmojis.isNotEmpty()) {
            return cachedEmojis
        }

        val allEmojis = loadMockdata(1000)
        val filteredEmojis =
            allEmojis.filter {
                it.name.contains(
                    search,
                    ignoreCase = true,
                )
            }

        // Add the new searched emojis to the cache
        for (emoji in filteredEmojis) {
            if (emoji !in emojisCache.values) {
                emojisCache[emoji.name] = emoji
            }
        }

        return filteredEmojis
    }
}
