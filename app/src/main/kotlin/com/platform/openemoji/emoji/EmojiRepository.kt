package com.platform.openemoji.emoji

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

// If this needs to be dynamic, it should be a state flow in EmojiCatalogueViewModel.
const val OVERVIEW_MAX_EMOJIS_PER_CATEGORY = 14

interface EmojiRepository {
    suspend fun getCategories(): List<String> = emptyList()

    /**
     * @param category
     * @return a map with only one key, the category, along with a list of all the emojis of that category.
     */
    suspend fun getEmojisOfCategory(category: String): Map<String, List<Emoji>> =
        emptyMap()

    suspend fun getOverviewEmojis(): Map<String, List<Emoji>> = emptyMap()

    suspend fun getPopularEmojis(limit: Int = Int.MAX_VALUE): List<Emoji> = emptyList()

    suspend fun getEmoji(name: String): Emoji? = null

    suspend fun searchEmojis(query: String): List<Emoji> = emptyList()
}

class EmojiMockDataRepository(
    private val context: Context?,
    private val testEmojis: List<Emoji>? = null,
    private val simulatedDelay: Long = 0,
) : EmojiRepository {
    private val catalogueCache: ConcurrentMap<String, Map<String, List<Emoji>>> =
        ConcurrentHashMap()
    private var overviewEmojisCache: Map<String, List<Emoji>>? = null

    // The gradual filling of this cache is not simulated here. That would be silly,
    // since this is temporary. getEmoji, the user of this cache, will in practise always
    // get an emoji from this cache that had already been loaded; otherwise, how did it
    // get the emoji's name?
    private val emojisByNameCache: Map<String, Emoji>
        get() = mockdata?.associateBy { it.name } ?: emptyMap()

    private var mockdata: List<Emoji>? = null

    // This method loads the emojis from the assets folder and is only needed when not using an API
    private suspend fun loadMockdata(): List<Emoji> {
        if (mockdata != null) return mockdata!!

        mockdata =
            if (context != null) {
                withContext(Dispatchers.IO) {
                    val inputStream = context.assets.open("emojis.json")
                    val size = inputStream.available()
                    val buffer = ByteArray(size)
                    inputStream.read(buffer)
                    inputStream.close()
                    val json = String(buffer, Charsets.UTF_8)
                    Json.decodeFromString<List<Emoji>>(json)
                }
            } else {
                testEmojis ?: emptyList()
            }

        return mockdata!!
    }

    override suspend fun getCategories(): List<String> {
        delay(simulatedDelay)
        val allEmojis = loadMockdata()
        return allEmojis.map { it.category }.distinct()
    }

    /**
     * @param category
     * @return a map with only one key, the category, along with a list of all the emojis of that category.
     */
    override suspend fun getEmojisOfCategory(category: String): Map<String, List<Emoji>> {
        val categoryEmojis = catalogueCache[category]
        if (categoryEmojis != null) {
            return categoryEmojis
        }

        delay(simulatedDelay)
        val allEmojis = loadMockdata()
        val emojisOfCategory =
            mapOf(
                category to
                    allEmojis.filter { it.category == category },
            )
        catalogueCache[category] = emojisOfCategory

        return emojisOfCategory
    }

    override suspend fun getOverviewEmojis(): Map<String, List<Emoji>> {
        if (overviewEmojisCache != null) {
            // It's safe to use !! as long as overviewEmojisCache is never changed
            // after it's been set, which it never should.
            return overviewEmojisCache!!
        }

        delay(simulatedDelay)
        val overviewEmojis =
            loadMockdata()
                .groupBy { it.category }
                .map { (category, emojis) ->
                    category to
                        emojis.sortedByDescending { it.popularity }
                            .take(OVERVIEW_MAX_EMOJIS_PER_CATEGORY)
                }
                .toMap()

        overviewEmojisCache = overviewEmojis
        return overviewEmojis
    }

    override suspend fun getPopularEmojis(limit: Int): List<Emoji> {
        delay(simulatedDelay)
        val allEmojis = loadMockdata()
        return allEmojis.sortedByDescending { it.popularity }.take(limit)
    }

    override suspend fun getEmoji(name: String): Emoji? {
        val emoji = emojisByNameCache[name]
        if (emoji != null) {
            return emoji
        }
        delay(simulatedDelay)
        return loadMockdata().find { it.name == name }
    }

    override suspend fun searchEmojis(query: String): List<Emoji> {
        delay(simulatedDelay)

        return loadMockdata().filter {
            it.name.contains(
                query,
                ignoreCase = true,
            )
        }
    }
}
