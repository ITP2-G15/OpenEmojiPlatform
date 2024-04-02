package com.platform.openemoji.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
) {
    IconButton(onClick = { navController.popBackStack() }) {
        Icon(
            Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = stringResource(R.string.back_arrow),
            tint = MaterialTheme.colorScheme.primary,
            modifier = modifier.size(48.dp).testTag("eventListBackButton"),
        )
    }
}
