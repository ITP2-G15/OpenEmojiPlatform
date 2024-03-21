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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.navigation.Screen

@Composable
fun UpcomingEvents(navController: NavController) {
    // TODO: Replace with real event data
    val event =
        Event(
            "St Patrick's day",
            "17.03",
            "https://emojipedia.org/_next/image?url=https%3A%2F%2Fem-content." +
                "zobj.net%2Fcontent%2Fevents%2FEarth_Day_PNG.png&w=1500&q=75",
            "https://emojipedia.org/st-patricks-day",
        )

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
                    Modifier.clickable {
                        navController.navigate(
                            Screen.EventListScreen.route,
                        )
                    }.testTag("showMoreEvents"),
            )
        }
        // TODO: Replace with real event data
        EventCard(event)
        EventCard(event)
    }
}
