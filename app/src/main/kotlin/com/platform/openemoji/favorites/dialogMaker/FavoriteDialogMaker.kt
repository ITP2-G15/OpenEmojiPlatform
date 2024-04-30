package com.platform.openemoji.favorites.dialogMaker

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.platform.openemoji.R

@Composable
fun FavoriteDialogMaker(
    showCreateDialog: MutableState<Boolean>,
    onCreate: () -> Unit,
) {
    if (showCreateDialog.value) {
        AlertDialog(
            onDismissRequest = { showCreateDialog.value = false },
            title = { Text(text = stringResource(R.string.create_sequence)) },
            text = {
                Text(
                    text = "",
                )
            },
            confirmButton = {
                Button(onClick = {
                    onCreate()
                    showCreateDialog.value = false
                }) {
                    Text(stringResource(R.string.delete))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showCreateDialog.value = false
                    },
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor =
                                MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                ) {
                    Text(stringResource(R.string.cancel))
                }
            },
        )
    }
}
