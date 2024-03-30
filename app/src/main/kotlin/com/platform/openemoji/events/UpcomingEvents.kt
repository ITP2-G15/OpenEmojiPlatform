package com.platform.openemoji.events

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.navigation.Screen

const val EVENTS_IN_UPCOMING_EVENTS = 2

@Composable
fun UpcomingEvents(
    eventViewModel: EventViewModel,
    navController: NavController,
) {
    val events by eventViewModel.events.collectAsState()

    Column(
        modifier = Modifier.testTag("upcomingEvents"),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.upcoming_events),
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = stringResource(R.string.show_more),
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                modifier =
                    Modifier
                        .clickable {
                            navController.navigate(
                                Screen.EventListScreen.route,
                            )
                        }
                        .testTag("showMoreEvents"),
            )
        }
        events?.let {
            for (event in it.take(EVENTS_IN_UPCOMING_EVENTS)) {
                EventCard(event)
            }
        }
    }
}
