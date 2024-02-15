package com.platform.openemoji

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "This is the search screen")
    }
}
