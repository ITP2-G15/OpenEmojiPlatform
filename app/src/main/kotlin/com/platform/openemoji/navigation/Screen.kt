package com.platform.openemoji.navigation

import com.platform.openemoji.emoji.Emoji

sealed class Screen(val route: String) {
    object SearchScreen  : Screen("SearchScreen")
    object EmojiDetailScreen : Screen("EmojiDetailScreen/{emoji}") {
        fun routeTo(emoji: Emoji) : String = "EmojiDetailScreen/$emoji"
    }

    object AllCategories : Screen("search/all")

}