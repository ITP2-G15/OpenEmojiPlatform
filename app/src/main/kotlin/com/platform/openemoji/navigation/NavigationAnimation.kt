package com.platform.openemoji.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition

fun slideEnterTransition(
    direction: AnimatedContentTransitionScope.SlideDirection =
        AnimatedContentTransitionScope.SlideDirection.Left,
): (AnimatedContentTransitionScope<*>.() -> EnterTransition) =
    {
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
