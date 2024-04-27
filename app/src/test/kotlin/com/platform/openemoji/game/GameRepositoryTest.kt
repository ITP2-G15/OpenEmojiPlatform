package com.platform.openemoji.game

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GameRepositoryTest {
    private lateinit var repository: GameMockDataRepository

    @Before
    fun setup() {
        val testLevels =
            listOf(
                Level(
                    "🤓🤓",
                    listOf("Nerd", "Clown", "Ole Andreas", "Andreas Li"),
                    0,
                ),
                Level(
                    "👍👍",
                    listOf("Smiley", "Nerd", "Whole Milk", "Thumbs Up"),
                    3,
                ),
                Level(
                    "⚽⚽",
                    listOf("Basketball", "Soccer", "Tennis", "Bae Suzy"),
                    1,
                ),
            )
        repository = GameMockDataRepository(null, testLevels)
    }

    @Test
    fun testGetCurrentLevel() =
        runBlocking {
            val expectedLevel =
                Level(
                    "🤓🤓",
                    listOf("Nerd", "Clown", "Ole Andreas", "Andreas Li"),
                    0,
                )
            val level = repository.getCurrentLevel()
            Assert.assertEquals(expectedLevel, level)
        }

    @Test
    fun testGetLevelCounter() =
        runBlocking {
            val levelCounter = repository.getLevelCounter()
            Assert.assertEquals(0, levelCounter)
        }

    @Test
    fun testIncrementLevelCounter() =
        runBlocking {
            val initialCounter = repository.getLevelCounter()
            Assert.assertEquals(0, initialCounter)

            repository.incrementLevelCounter()

            val updatedCounter = repository.getLevelCounter()
            Assert.assertEquals(1, updatedCounter)
        }
}
