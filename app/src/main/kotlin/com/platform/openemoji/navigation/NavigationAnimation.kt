package com.platform.openemoji.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn

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
