package com.platform.openemoji.game

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GameRepositoryTest {
    private lateinit var repository: GameMockDataRepository

    @Before
    fun setup() {
        val testNews =
            listOf(
                Level(
                    "🤓",
                    listOf("Nerd", "Clown", "Ole Andreas", "Andreas Li"),
                    0,
                ),
                Level(
                    "👍",
                    listOf("Smiley", "Nerd", "Whole Milk", "Thumbs Up"),
                    3,
                ),
                Level(
                    "⚽",
                    listOf("Basketball", "Soccer", "Tennis", "Suzy Bae"),
                    1,
                ),
            )
        repository = GameMockDataRepository(null, testNews)
    }

    @Test
    fun testGetLevels() =
        runBlocking {
            val levels = repository.getLevels()
            Assert.assertEquals(3, levels.size)
        }

    @Test
    fun getLevel() =
        runBlocking {
            val expectedLevel =
                Level(
                    "🤓",
                    listOf("Nerd", "Clown", "Ole Andreas", "Andreas Li"),
                    0,
                )
            val level = repository.getLevel(0)
            Assert.assertEquals(expectedLevel, level)
        }
}
