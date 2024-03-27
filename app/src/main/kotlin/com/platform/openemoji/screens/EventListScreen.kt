package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.events.Event
import com.platform.openemoji.events.EventCard
import com.platform.openemoji.navigation.BackButtonNavigation

@Composable
fun EventListScreen(navController: NavController) {
    // TODO: Replace with real event data.
    val event =
        Event(
            "St Patrick's day",
            "17.03",
            "https://emojipedia.org/_next/image?url=https%3A%2F%2Fem-content." +
                "zobj.net%2Fcontent%2Fevents%2FEarth_Day_PNG.png&w=1500&q=75",
            "https://emojipedia.org/st-patricks-day",
        )

    Column(
        modifier =
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 8.dp).testTag("eventListScreen"),
    ) {
        /**
         * Arrow back button, takes you back to previous location.
         */
        BackButtonNavigation(
            navController = navController,
            modifier = Modifier.testTag("eventListBackButton"),
        )
        Text(
            text = stringResource(R.string.eventsandtopics),
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        // TODO: Replace with real event data.
        EventCard(event)
        EventCard(event)
        EventCard(event)
        EventCard(event)
        EventCard(event)
        EventCard(event)
        EventCard(event)
        EventCard(event)
    }
}
