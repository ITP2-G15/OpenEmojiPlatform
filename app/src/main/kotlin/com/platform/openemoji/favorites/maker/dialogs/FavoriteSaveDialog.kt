package com.platform.openemoji.favorites.maker.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.platform.openemoji.R

private const val MIN_NAME_LENGTH = 3
private const val MAX_NAME_LENGTH = 35

@Composable
fun FavoriteSaveDialog(
    showSaveDialog: MutableState<Boolean>,
    name: MutableState<TextFieldValue>,
    nameError: MutableState<String?>,
    onSave: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val nameLengthError =
        stringResource(
            R.string.name_length_error,
            MIN_NAME_LENGTH,
            MAX_NAME_LENGTH,
        )

    if (showSaveDialog.value) {
        AlertDialog(
            onDismissRequest = { showSaveDialog.value = false },
            title = {
                Text(
                    stringResource(
                        R.string.enter_name_for_favorite,
                    ),
                )
            },
            text = {
                Column {
                    TextField(
                        value = name.value,
                        onValueChange = { newValue ->
                            if (newValue.text.length !in
                                MIN_NAME_LENGTH..MAX_NAME_LENGTH
                            ) {
                                nameError.value = nameLengthError
                            } else {
                                nameError.value = null
                            }
                            name.value = newValue
                        },
                        label = {
                            Text(
                                stringResource(
                                    R.string.name,
                                ),
                            )
                        },
                        modifier = Modifier.focusRequester(focusRequester),
                    )

                    if (nameError.value != null) {
                        Text(nameError.value!!, color = MaterialTheme.colorScheme.error)
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onSave()
                        showSaveDialog.value = false
                    },
                    enabled =
                        name.value.text.length in
                            MIN_NAME_LENGTH..MAX_NAME_LENGTH,
                ) {
                    Text(
                        stringResource(
                            R.string.save,
                        ),
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { showSaveDialog.value = false },
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor =
                                MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                ) {
                    Text(
                        stringResource(
                            R.string.cancel,
                        ),
                    )
                }
            },
        )
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}
