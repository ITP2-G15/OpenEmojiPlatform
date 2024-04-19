package com.platform.openemoji.favorites

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FavoritesRepositoryTest {
    private lateinit var favoritesRepository: FavoritesDataRepository
    private val testFavorites =
        mutableListOf(
            Favorite(
                "name1",
                arrayOf("code1", "code2", "code3"),
            ),
            Favorite(
                "name2",
                arrayOf("code2", "code3", "code4"),
            ),
            Favorite(
                "name3",
                arrayOf("code4", "code8", "code4"),
            ),
        )

    @Before
    fun setUp() {
        favoritesRepository = FavoritesDataRepository(null, testFavorites)
    }

    @Test
    fun testGetFavorites() =
        runBlocking {
            val actualFavorites = favoritesRepository.getFavorites().first()

            assertEquals(testFavorites, actualFavorites)
        }

    @Test
    fun testAddFavorite() =
        runBlocking {
            val favorite = Favorite("name4", arrayOf("code2", "code2", "code3"))
            favoritesRepository.addFavorite(favorite)

            val actualFavorites = favoritesRepository.getFavorites().first()

            assertTrue(actualFavorites.contains(favorite))
        }

    @Test
    fun testDeleteFavorite() =
        runBlocking {
            val favorite = testFavorites[1]
            favoritesRepository.deleteFavorite(favorite)

            val actualFavorites = favoritesRepository.getFavorites().first()

            assertFalse(actualFavorites.contains(favorite))
        }
}
