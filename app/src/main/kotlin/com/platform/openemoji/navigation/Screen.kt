package com.platform.openemoji.navigation

/**
 * Turns string into NavigationItem object, use instead of strings.
 * @param String type of a route.
 */
sealed class Screen(val route: String, val navOrder: Int? = null) {
    /**
     * Search Screen NavigationItem with nested NavigationItems.
     * In order to navigate with correct animations, a navigation order is needed
     * for it to transition correctly.
     * Therefore added as a parameter to screen in form of navOrder.
     */
    object HomeScreen : Screen("HomeScreen", 0)

    object SearchScreen : Screen("SearchScreen", 1)

    object EmojiDetailScreen : Screen("emoji", 2)

    object NewsListScreen : Screen("NewsListScreen", 3)

    object EventListScreen : Screen("EventListScreen", 4)

    object FavoritesScreen : Screen("FavoritesScreen", 5)

    /**
     * Adds arguments to a navigationItem.
     * If you want to use arguments use ".withArgs("args")" instead of ".route".
     * @param String of arguments.
     * @return String of arguments to object.
     */
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
