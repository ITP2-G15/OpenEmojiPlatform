package com.platform.openemoji.events

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.R

@Composable
fun EventList(navController: NavController) {
    val event =
        Event(
            "1",
            "St Patrick's day",
            "17.03",
            "https://emojipedia.org/st-patricks-day",
        )

    Column(
        modifier =
            Modifier.verticalScroll(rememberScrollState())
                .padding(horizontal = 8.dp),
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back_arrow),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp),
            )
        }
        Text(
            text = "Upcoming Events",
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        EventUi(event)
        EventUi(event)
        EventUi(event)
        EventUi(event)
        EventUi(event)
        EventUi(event)
        EventUi(event)
        EventUi(event)
    }
}
