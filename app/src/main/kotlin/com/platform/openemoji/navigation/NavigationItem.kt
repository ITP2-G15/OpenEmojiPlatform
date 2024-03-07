package com.platform.openemoji.navigation

/**
 * Turns string into NavigationItem object, use instead of strings.
 * @param String type of a route.
 */
sealed class NavigationItem(val route: String) {
    //Search Screen NavigationItem with nested NavigationItems.
    object SearchScreen  : NavigationItem("SearchScreen") {
        object AllCategories : NavigationItem("search/all")
        object Categories : NavigationItem("search") {
            fun routeTo(category: String): String = "$route/$category"
        }
    }
    object EmojiDetailScreen : NavigationItem("emoji")

    /**
     * Adds arguments to a navigationItem.
     * If you want to use arguments use ".withArgs("args")" instead of ".route".
     * @param String of arguments.
     * @return String of arguments to object.
     */
    fun withArgs(vararg args : String): String {
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}