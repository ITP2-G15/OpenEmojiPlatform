package com.platform.openemoji.favorites

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.platform.openemoji.R

@Composable
fun FavoriteCard(favorite: Favorite) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = favorite.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp).testTag("favoriteName"),
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ) {
                val emojiRegex = "[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+".toRegex()
                val emojis =
                    emojiRegex.findAll(
                        favorite.emojiSequence,
                    ).map { it.value }.toList()

                Text(
                    text = emojis.joinToString(""),
                    style = MaterialTheme.typography.displaySmall,
                    // Set the maximum number of lines before overflow
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 8.dp).testTag("emojiSequence"),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(onClick = {
                        clipboardManager.setText(AnnotatedString(favorite.emojiSequence))
                        Toast.makeText(
                            context,
                            R.string.copy_to_clipboard,
                            Toast.LENGTH_SHORT,
                        ).show()
                    }, modifier = Modifier.testTag("copyButton")) {
                        Icon(
                            Icons.Filled.ContentCopy,
                            contentDescription = stringResource(R.string.copy),
                        )
                        Text(text = stringResource(R.string.copy))
                    }
                    Button(onClick = {
                        showDialog = true
                    }, modifier = Modifier.testTag("deleteButton")) {
                        Icon(
                            Icons.Filled.DeleteOutline,
                            contentDescription = stringResource(R.string.delete),
                        )
                        Text(text = stringResource(R.string.delete))
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = stringResource(R.string.delete_confirmation)) },
            text = {
                Text(
                    text = stringResource(R.string.delete_confirmation_message_sequence),
                )
            },
            confirmButton = {
                Button(onClick = {
                    // HANDLE DELETE ACTION HERE
                    showDialog = false
                }, modifier = Modifier.testTag("confirmDeleteButton")) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog = false
                }, modifier = Modifier.testTag("dismissDeleteButton")) {
                    Text("No")
                }
            },
        )
    }
}
