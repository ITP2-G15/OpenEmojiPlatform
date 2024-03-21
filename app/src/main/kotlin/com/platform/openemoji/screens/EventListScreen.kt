package com.platform.openemoji.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Composable
fun EventListScreen(navController: NavController) {
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
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back_arrow),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp).testTag("eventListBackButton"),
            )
        }
        Text(
            text = stringResource(R.string.eventsandtopics),
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
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
