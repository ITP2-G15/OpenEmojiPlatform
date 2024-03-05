package com.platform.openemoji.navigation

import com.platform.openemoji.emoji.Emoji

enum class Screen {
    SEARCHSCREEN,
    EMOJIDETAILSCREEN
}
sealed class NavigationItem(val route: String) {
    object SearchScreen  : NavigationItem(Screen.SEARCHSCREEN.name)
    object EmojiDetailScreen : NavigationItem(Screen.EMOJIDETAILSCREEN.name + "/emoji") {
        fun routeTo(emoji: Emoji) : String = "${Screen.SEARCHSCREEN}/$emoji"
    }
    object AllCategories : NavigationItem("search/all")

}