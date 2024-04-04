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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.platform.openemoji.R

@Composable
fun Sequence(favorite: Favorite) {
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
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp),
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
                emojis.chunked(5).forEach { chunk ->
                    Text(
                        text = chunk.joinToString(""),
                        fontSize = 22.sp,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                }
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
                    }) {
                        Icon(Icons.Filled.ContentCopy, contentDescription = "Copy Icon")
                        Text(text = stringResource(R.string.copy))
                    }
                    Button(onClick = {
                        showDialog = true
                    }) {
                        Icon(
                            Icons.Filled.DeleteOutline,
                            contentDescription = "Delete Icon",
                        )
                        Text(text = context.getString(R.string.delete_sequence))
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = context.getString(R.string.delete_confirmation)) },
            text = {
                Text(
                    text = context.getString(R.string.delete_confirmation_message),
                )
            },
            confirmButton = {
                Button(onClick = {
                    // Handle delete action here
                    showDialog = false
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("No")
                }
            },
        )
    }
}
