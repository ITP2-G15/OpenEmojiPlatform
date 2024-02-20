package com.platform.openemoji.emoji.catalogue

import com.platform.openemoji.emoji.Emoji

class EmojiCatalogue(
    emojis: List<Emoji>,
) {
    val byCategory: Map<String, List<Emoji>> = emojis.groupBy { it.category }
    val categories: Set<String> = byCategory.keys

    fun bySubCategory(category: String): Map<String, List<Emoji>>? = null
}
