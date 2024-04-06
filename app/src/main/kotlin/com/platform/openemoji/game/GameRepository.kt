package com.platform.openemoji.game

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface GameRepository {
    suspend fun getAllLevels(): List<Level> = emptyList()

    suspend fun getCurrentLevel(): Level? = null

    suspend fun getLevelCounter(): Flow<Int> = flowOf(0)

    suspend fun incrementLevelCounter() = Unit
}

class GameMockDataRepository(
    private val context: Context?,
    private val testLevels: List<Level>? = null,
    private var testLevelCounter: Int = 0,
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

    // persistent storage using DataStore
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "gameData",
    )

    // Preferences key used to store the current level counter
    private val levelCounter = intPreferencesKey("currentLevel")

    override suspend fun getLevelCounter(): Flow<Int> {
        return context?.dataStore?.data?.map { gameData ->
            // Retrieve the current level counter or default to 0
            gameData[levelCounter] ?: 0
        }
            ?: flowOf(testLevelCounter)
    }

    override suspend fun getAllLevels(): List<Level> {
        return loadMockdata()
    }

    override suspend fun getCurrentLevel(): Level? {
        val levels = loadMockdata()
        val levelCounter = getLevelCounter().first()

        return levels.getOrNull(levelCounter)
    }

    override suspend fun incrementLevelCounter() {
        if (context != null) {
            context.dataStore.edit { gameData ->
                val currentLevel = gameData[levelCounter] ?: 0
                gameData[levelCounter] = currentLevel + 1
            }
        } else {
            testLevelCounter += 1
        }
    }
}
