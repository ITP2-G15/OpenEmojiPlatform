package com.platform.openemoji.favorites.card.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.platform.openemoji.R
import com.platform.openemoji.favorites.Favorite

@Composable
fun FavoriteDeleteDialog(
    favorite: Favorite,
    showDeleteDialog: MutableState<Boolean>,
    onDelete: () -> Unit,
) {
    if (showDeleteDialog.value) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog.value = false },
            title = { Text(text = stringResource(R.string.delete_confirmation)) },
            text = {
                Text(
                    text =
                        stringResource(
                            R.string.delete_confirmation_message_sequence,
                            favorite.name,
                        ),
                )
            },
            confirmButton = {
                Button(onClick = {
                    onDelete()
                    showDeleteDialog.value = false
                }, modifier = Modifier.testTag("confirmDeleteButton")) {
                    Text(stringResource(R.string.delete))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDeleteDialog.value = false
                    },
                    modifier = Modifier.testTag("dismissDeleteButton"),
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
