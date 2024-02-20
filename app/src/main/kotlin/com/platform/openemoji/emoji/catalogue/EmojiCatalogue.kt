package com.platform.openemoji.emoji.catalogue

import com.platform.openemoji.emoji.Emoji

class EmojiCatalogue(
    emojis: List<Emoji>,
) {
    val byCategory: Map<String, List<Emoji>> = emojis.groupBy { it.category }
    val categories: List<String> = byCategory.keys.toList()

    fun bySubCategory(category: String): Map<String, List<Emoji>>? =
        byCategory[category]?.groupBy { it.subCategory }
}
