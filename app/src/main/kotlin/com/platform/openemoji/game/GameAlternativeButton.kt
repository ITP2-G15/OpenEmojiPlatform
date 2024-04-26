package com.platform.openemoji.game

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun GameAlternativeButton(
    alternative: String,
    onClick: () -> Unit,
    isWrongAnswer: Boolean,
) {
    val containerColor =
        if (isWrongAnswer) {
            MaterialTheme.colorScheme.error
        } else {
            MaterialTheme.colorScheme.primary
        }

    val contentColor =
        if (isWrongAnswer) {
            MaterialTheme.colorScheme.onError
        } else {
            MaterialTheme.colorScheme.onPrimary
        }

    Button(
        modifier =
            Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .testTag("GameAlternativeButton"),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
        onClick = onClick,
    ) {
        Text(
            text = alternative,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
        )
    }
}
