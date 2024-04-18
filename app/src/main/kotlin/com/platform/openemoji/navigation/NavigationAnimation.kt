package com.platform.openemoji.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavController

/**
 * Used to select the transition based on the previous and current route
 * NOTE: This can only be used with correct input.
 * @param navController The navigation controller
 * @param transitionType The type of transition
 * @return The transition based on the previous and current route
 */
fun selectEnterTransition(
    navController: NavController,
): (AnimatedContentTransitionScope<*>) -> EnterTransition {
    val previousRouteOrder = navController.previousBackStackEntry?.destination?.navOrder
    val currentRouteOrder = navController.currentBackStackEntry?.destination?.navOrder

    if (previousRouteOrder != null) {
        return if (previousRouteOrder > currentRouteOrder!!) {
            slideEnterTransition(
                AnimatedContentTransitionScope.SlideDirection.Left,
            )
        } else {
            slideEnterTransition(
                AnimatedContentTransitionScope.SlideDirection.Right,
            )
        }
    }
    return slideEnterTransition()
}

fun selectExitTransition(
    navController: NavController,
): (AnimatedContentTransitionScope<*>) -> ExitTransition {
    val previousRouteOrder = navController.previousBackStackEntry?.destination?.navOrder
    val currentRouteOrder = navController.currentBackStackEntry?.destination?.navOrder

    if (previousRouteOrder != null) {
        return if (previousRouteOrder > currentRouteOrder!!) {
            slideExitTransition(
                AnimatedContentTransitionScope.SlideDirection.Left,
            )
        } else {
            slideExitTransition(
                AnimatedContentTransitionScope.SlideDirection.Right,
            )
        }
    }
    return slideExitTransition()
}

fun slideEnterTransition(
    direction: AnimatedContentTransitionScope.SlideDirection =
        AnimatedContentTransitionScope.SlideDirection.Left,
): (AnimatedContentTransitionScope<*>.() -> EnterTransition) =
    {
        fadeIn(
            tween(
                durationMillis = 300,
            ),
        ) +
            slideIntoContainer(
                towards = direction,
            )
    }

fun slideExitTransition(
    direction: AnimatedContentTransitionScope.SlideDirection =
        AnimatedContentTransitionScope.SlideDirection.Right,
): (AnimatedContentTransitionScope<*>.() -> ExitTransition) =
    {
        slideOutOfContainer(
            towards = direction,
        )
    }
