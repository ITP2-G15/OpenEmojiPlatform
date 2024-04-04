package com.platform.openemoji.game

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface GameRepository {
    suspend fun getLevels(): List<Level> = emptyList()

    suspend fun getLevel(currentLevel: Int): Level? = null
}

class GameMockDataRepository(
    private val context: Context?,
    private val testLevels: List<Level>? = null,
    private val simulatedDelay: Long = 0,
) : GameRepository {
    // This is only needed when not using an API
    private var mockdata: List<Level>? = null

    private suspend fun loadMockdata(): List<Level> {
        if (mockdata != null) return mockdata!!

        // Simulate a delay to show the loading state
        delay(simulatedDelay)

        mockdata =
            if (context != null) {
                withContext(Dispatchers.IO) {
                    val inputStream = context.assets.open("levels.json")
                    val size = inputStream.available()
                    val buffer = ByteArray(size)
                    inputStream.read(buffer)
                    inputStream.close()
                    val json = String(buffer, Charsets.UTF_8)
                    Json.decodeFromString<List<Level>>(json)
                }
            } else {
                testLevels ?: emptyList()
            }

        return mockdata!!
    }

    override suspend fun getLevels(): List<Level> {
        return loadMockdata()
    }

    override suspend fun getLevel(currentLevel: Int): Level? {
        val levels = loadMockdata()
        return if (currentLevel in levels.indices) levels[currentLevel] else null
    }
}
