package com.platform.openemoji.emoji.catalogue

import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.EmojiRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class EmojiCatalogueViewModelTest {
    lateinit var catalogueViewModel: EmojiCatalogueViewModel

    private fun emoji() =
        Emoji(
            "",
            "",
            "",
            "",
            1,
            "",
            "",
            0f,
            0f,
        )

    @Before
    fun setUp() {
        catalogueViewModel =
            EmojiCatalogueViewModel(
                object : EmojiRepository {
                    override suspend fun getPopularEmojis(limit: Int): List<Emoji> =
                        listOf(emoji())
                },
            )
    }

    @Test
    fun testMostPopular() =
        runBlocking {
            assert(catalogueViewModel.mostPopularEmojis.value.isEmpty())
            catalogueViewModel.loadMostPopularEmojis()

            assert(catalogueViewModel.mostPopularEmojis.value.isNotEmpty())
        }
}
