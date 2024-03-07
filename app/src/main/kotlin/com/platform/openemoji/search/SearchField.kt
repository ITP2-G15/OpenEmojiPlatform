package com.platform.openemoji.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.platform.openemoji.R

@Composable
fun SearchField(query: MutableState<String>) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = query.value,
        onValueChange = { newValue -> query.value = newValue },
        label = {
            Text(stringResource(R.string.search_label))
        },
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("searchTextField"),
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions =
            KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
    )
}
