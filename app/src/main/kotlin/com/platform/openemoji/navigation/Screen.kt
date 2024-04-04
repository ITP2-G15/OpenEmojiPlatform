package com.platform.openemoji.navigation

/**
 * Turns string into NavigationItem object, use instead of strings.
 * @param String type of a route.
 */
sealed class Screen(val route: String) {
    // Search Screen NavigationItem with nested NavigationItems.
    object HomeScreen : Screen("HomeScreen")

    object SearchScreen : Screen("SearchScreen")

    object GameScreen : Screen("GameScreen")

    object EmojiDetailScreen : Screen("emoji")

    object NewsListScreen : Screen("NewsListScreen")

    object EventListScreen : Screen("EventListScreen")

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
