package com.platform.openemoji.navigation

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import com.platform.openemoji.R

@Composable
fun ShowMoreNavigation(
    navController: NavController,
    screen: Screen,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.show_more),
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline,
        modifier =
            modifier.clickable {
                navController.navigate(
                    screen.route,
                )
            },
    )
}
