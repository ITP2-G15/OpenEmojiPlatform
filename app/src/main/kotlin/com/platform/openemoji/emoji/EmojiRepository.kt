package com.platform.openemoji.emoji

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

// The overview category is a special category that contains a limited number of emojis from each category
// This can also be retrieved from the API or changed to a different view
const val OVERVIEW = "Overview"

// If this needs to be dynamic, it should be a state flow in EmojiCatalogueViewModel.
const val OVERVIEW_MAX_EMOJIS_PER_CATEGORY = 14

class EmojiRepository(
    private val context: Context,
) {
    private val simulatedDelay: Long = 500
    private val catalogueCache: ConcurrentMap<String, Map<String, List<Emoji>>> =
        ConcurrentHashMap()

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

    suspend fun getEmojisOfCategory(category: String): Map<String, List<Emoji>> {
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

    suspend fun getOverviewEmojis(): Map<String, List<Emoji>> {
        val cachedOverviewEmojis = catalogueCache[OVERVIEW]
        if (cachedOverviewEmojis != null) {
            return cachedOverviewEmojis
        }

        delay(simulatedDelay)
        val overviewEmojis =
            loadMockdata()
                .groupBy { it.category }
                .map { (category, emojis) ->
                    category to emojis.take(OVERVIEW_MAX_EMOJIS_PER_CATEGORY)
                }
                .toMap()

        catalogueCache[OVERVIEW] = overviewEmojis
        return overviewEmojis
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
