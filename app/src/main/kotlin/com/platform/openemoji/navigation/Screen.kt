package com.platform.openemoji.navigation

/**
 * Turns string into NavigationItem object, use instead of strings.
 * @param String type of a route.
 * @param Int type of a navigation order.
 */
sealed class Screen(val route: String, val id: Int? = null) {
    /**
     * Search Screen NavigationItem with nested NavigationItems.
     * In order to navigate with correct animations, a navigation order is needed
     * for it to transition correctly.
     * Therefore added as a parameter to screen in form of id, as this is a property
     * of navDestination making it accessible through that.
     * Remember correct orderId is needed for correct transition.
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
