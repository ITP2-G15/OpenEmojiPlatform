package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.events.EventCard
import com.platform.openemoji.events.EventViewModel
import com.platform.openemoji.navigation.BackButtonNavigation

@Composable
fun EventListScreen(
    eventViewModel: EventViewModel,
    navController: NavController,
) {
    val events by eventViewModel.events.collectAsState()

    Column(
        modifier =
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 8.dp)
                .testTag("eventListScreen"),
    ) {
        BackButtonNavigation(
            navController = navController,
            modifier = Modifier.testTag("eventListBackButton"),
        )
        Text(
            text = stringResource(R.string.events_and_topics),
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        events?.let {
            for (event in it) {
                EventCard(event)
            }
        }
    }
}
