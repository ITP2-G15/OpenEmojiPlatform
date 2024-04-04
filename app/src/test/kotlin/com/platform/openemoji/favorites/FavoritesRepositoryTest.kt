package com.platform.openemoji.favorites

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FavoritesRepositoryTest {
    private lateinit var favoritesRepository: FavoritesDataRepository
    private val testFavorites =
        mutableListOf(
            Favorite(
                "name1",
                "sequence1",
            ),
            Favorite(
                "name2",
                "sequence2",
            ),
            Favorite(
                "name3",
                "sequence3",
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
            val favorite = Favorite("name4", "sequence4")
            favoritesRepository.addFavorite(favorite)

            val actualFavorites = favoritesRepository.getFavorites().first()

            assertTrue(actualFavorites.contains(favorite))
        }
}
