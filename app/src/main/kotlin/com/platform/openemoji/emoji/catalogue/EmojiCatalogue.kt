package com.platform.openemoji.emoji.catalogue

import com.platform.openemoji.emoji.Emoji

/* This is the class to extend with other ways to access emojis, such as search,
    so that emojis are only loaded once - by the single instance of this class.
 */

/**
 * A singleton data structure to hold and categorize all the emojis.
 * It needs to be populated with emojis using ".populate(...)".
 */
class EmojiCatalogue private constructor() {
    // Singleton instance provider
    companion object {
        @Volatile
        private var instance: EmojiCatalogue? = null

        fun get() =
            instance ?: synchronized(this) {
                instance ?: EmojiCatalogue().also { instance = it }
            }
    }

    /**
     * Once populated, this is a map from emoji.category to all the emojis
     * that belong to that category.
     */
    var byCategory: Map<String, List<Emoji>> = emptyMap()
        private set

    /**
     * Once populated, this is a list of all emojis
     */
    val allEmojis: List<Emoji>
        get() = emojiByTitle.values.toList()

    private var emojiByTitle: Map<String, Emoji> = emptyMap()

    fun populate(emojis: List<Emoji>) {
        emojiByTitle = emojis.associateBy { it.title }
        byCategory = emojis.groupBy { it.category }
    }

    /**
     * Once populated, this list contains all the categories that were present
     * in the populating list of emojis.
     */
    val categories: List<String>
        get() = byCategory.keys.toList()

    /**
     * Once populated, generates a map from emoji.subCategory to all the emojis
     * that are under both the category and the subCategory, e.g. that are under
     * Activities > Sport.
     *
     * @param category the parent of the subcategories.
     * @return the categorized emojis by subcategory or null if the category does not exist.
     */
    fun bySubCategory(category: String): Map<String, List<Emoji>>? =
        byCategory[category]?.groupBy { it.subCategory }

    /**
     * Search based on emoji.title. Ignores uppercase.
     * @param query query string to search for emojis.
     * @return list of emojis matching the query.
     */
    fun search(query: String): List<Emoji> {
        val queryLowerCase = query.lowercase()
        return allEmojis.filter {
            it.title.lowercase().contains(queryLowerCase)
        }
    }

    /**
     * Returns emoji based on title.
     * @param title title of an emoji.
     * @return emoji matching title.
     */
    fun emoji(title: String): Emoji? = emojiByTitle[title]
}
