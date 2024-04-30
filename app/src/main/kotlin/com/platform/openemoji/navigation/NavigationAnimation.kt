package com.platform.openemoji.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

/**
 * Used to select the transition based on the previous and current route
 * NOTE: This can only be used with correct input.
 * @param navController The navigation controller
 * @param transitionType The type of transition
 * @return The transition based on the previous and current route
 */
fun selectEnterTransition(
    currentOrderValue: Int? = null,
    previousOrderValue: Int? = null,
): (AnimatedContentTransitionScope<*>) -> EnterTransition {
    if (previousOrderValue != null) {
        return if (previousOrderValue > currentOrderValue!!) {
            slideEnterTransition(
                AnimatedContentTransitionScope.SlideDirection.Right,
            )
        } else {
            return slideEnterTransition(
                AnimatedContentTransitionScope.SlideDirection.Left,
            )
        }
    }
    return slideEnterTransition()
}

fun selectExitTransition(
    currentOrderValue: Int? = null,
    previousOrderValue: Int? = null,
): (AnimatedContentTransitionScope<*>) -> ExitTransition {
    if (previousOrderValue != null) {
        return if (previousOrderValue > currentOrderValue!!) {
            slideExitTransition(
                AnimatedContentTransitionScope.SlideDirection.Right,
            )
        } else {
            return slideExitTransition(
                AnimatedContentTransitionScope.SlideDirection.Left,
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
        AnimatedContentTransitionScope.SlideDirection.Left,
): (AnimatedContentTransitionScope<*>.() -> ExitTransition) =
    {
        fadeOut(
            tween(
                durationMillis = 300,
            ),
        ) +
            slideOutOfContainer(
                towards = direction,
            )
    }
