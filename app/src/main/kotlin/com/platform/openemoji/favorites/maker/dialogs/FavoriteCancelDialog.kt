package com.platform.openemoji.favorites.maker.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.platform.openemoji.R

@Composable
fun FavoriteCancelDialog(
    showCancelDialog: MutableState<Boolean>,
    onCancel: () -> Unit,
) {
    if (showCancelDialog.value) {
        AlertDialog(
            onDismissRequest = { showCancelDialog.value = false },
            title = { Text(stringResource(R.string.confirm_clear_title)) },
            text = { Text(stringResource(R.string.confirm_clear_message)) },
            confirmButton = {
                Button(
                    onClick = {
                        onCancel()
                        showCancelDialog.value = false
                    },
                ) {
                    Text(stringResource(R.string.clear))
                }
            },
            dismissButton = {
                Button(
                    onClick = { showCancelDialog.value = false },
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                ) {
                    Text(stringResource(R.string.cancel))
                }
            },
        )
    }
}
