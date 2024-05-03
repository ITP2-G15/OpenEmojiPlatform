package com.platform.openemoji.favorites.maker

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.platform.openemoji.R
import com.platform.openemoji.favorites.FavoritesViewModel

@Composable
fun FavoriteMakerTextInput(
    text: MutableState<TextFieldValue>,
    favoritesViewModel: FavoritesViewModel,
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = text.value,
        onValueChange = { newText ->
            text.value = newText
        },
        label = {
            Text(
                stringResource(
                    R.string.add_custom_favorite_text,
                ),
            )
        },
        keyboardActions =
            KeyboardActions(onDone = {
                if (text.value.text.isNotEmpty()) {
                    favoritesViewModel.appendToCurrentFavoriteEmojiCodes(text.value.text)
                    text.value = TextFieldValue("")
                }
                focusManager.clearFocus()
            }),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 8.dp),
    )
}
