package com.platform.openemoji.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R

@Composable
fun BackButtonNavigation(
    navController: NavController,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    // Track if a navigation is in progress (to prevent multiple pops of backstack)
    // NOTE: Since this is a composable, it will reset on page reloads and when
    // navigation is complete.
    var navigationInProgress by rememberSaveable { mutableStateOf(false) }

    IconButton(onClick = {
        // Check if a navigation is already in progress
        if (!navigationInProgress) {
            // If not, pop the back stack and set navigationInProgress to true
            navController.popBackStack()
            navigationInProgress = true
            onClick()
        }
    }) {
        Icon(
            Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = stringResource(R.string.back_arrow),
            tint = MaterialTheme.colorScheme.primary,
            modifier = modifier.size(48.dp).testTag("eventListBackButton").padding(4.dp),
        )
    }
}
