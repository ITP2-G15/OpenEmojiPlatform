package com.platform.openemoji.favorites.makerDialog

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.platform.openemoji.R
import com.platform.openemoji.RepositoryStore
import com.platform.openemoji.emoji.IconFavorite
import com.platform.openemoji.favorites.FavoritesViewModel
import com.platform.openemoji.favorites.maker.FavoriteMakerTextInput
import com.platform.openemoji.favorites.maker.dialogs.FavoriteSaveDialog
import com.platform.openemoji.search.SearchField
import com.platform.openemoji.search.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FavoriteDialogMaker(
    favoritesViewModel: FavoritesViewModel,
    showCreateDialog: MutableState<Boolean>,
    repositories: RepositoryStore =
        LocalContext.current.applicationContext as RepositoryStore,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val currentFavorite by favoritesViewModel.currentFavorite.collectAsState()
    val localCurrentFavorite = currentFavorite

    val searchViewModel: SearchViewModel =
        viewModel {
            SearchViewModel(repositories.emojiRepository)
        }

    val searchQuery by searchViewModel.searchQuery.collectAsState()
    val searchResults by searchViewModel.searchResults
        .collectAsState(emptyList())

    val searchResultsAreLoading by searchViewModel.searchResultsAreLoading
        .collectAsState()

    val showSaveDialog = remember { mutableStateOf(false) }
    val nameError = remember { mutableStateOf<String?>(null) }
    val name = remember { mutableStateOf(TextFieldValue("")) }
    val customText = remember { mutableStateOf(TextFieldValue("")) }

    FavoriteSaveDialog(
        showSaveDialog = showSaveDialog,
        name = name,
        nameError = nameError,
        onSave = {
            CoroutineScope(Dispatchers.Main).launch {
                favoritesViewModel.addCurrentFavoriteToFavorites(
                    name = name.value.text,
                )
            }

            Toast.makeText(
                context,
                R.string.save_sequence_toast_message,
                Toast.LENGTH_SHORT,
            ).show()

            showCreateDialog.value = false
            searchViewModel.search("")
        },
    )

    if (showCreateDialog.value) {
        AlertDialog(
            modifier = Modifier.testTag("createFavoriteDialog"),
            onDismissRequest = { showCreateDialog.value = false },
            title = { Text(text = stringResource(R.string.create_sequence)) },
            text = {
                Column {
                    Card(
                        modifier =
                            Modifier
                                .fillMaxWidth().padding(
                                    vertical = 8.dp,
                                ),
                    ) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier =
                                    Modifier.padding(8.dp),
                            ) {
                                Text(
                                    text =
                                        localCurrentFavorite?.emojiCodes
                                            ?.joinToString(
                                                "",
                                            ) ?: "",
                                    style =
                                        MaterialTheme.typography
                                            .displaySmall,
                                    modifier = Modifier.padding(start = 8.dp),
                                )
                            }

                            FavoriteMakerTextInput(customText, favoritesViewModel)

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier =
                                    Modifier
                                        .fillMaxWidth().padding(horizontal = 12.dp)
                                        .padding(bottom = 8.dp),
                            ) {
                                Button(
                                    onClick = {
                                        favoritesViewModel
                                            .appendToCurrentFavoriteEmojiCodes(
                                                customText.value.text,
                                            )
                                        customText.value = TextFieldValue("")
                                        focusManager.clearFocus()
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors =
                                        ButtonDefaults.buttonColors(
                                            containerColor =
                                                MaterialTheme.colorScheme.tertiary,
                                        ),
                                ) {
                                    Row {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription =
                                                stringResource(
                                                    R.string.add_text_button_description,
                                                ),
                                            tint = MaterialTheme.colorScheme.onTertiary,
                                        )

                                        Icon(
                                            imageVector = Icons.Default.EditNote,
                                            contentDescription =
                                                stringResource(
                                                    R.string.add_text_button_description,
                                                ),
                                            tint = MaterialTheme.colorScheme.onTertiary,
                                            modifier =
                                                Modifier.padding(
                                                    start = 3.dp,
                                                ),
                                        )
                                    }
                                }

                                Button(
                                    onClick = {
                                        favoritesViewModel
                                            .removeLastEmojiCodeFromCurrentFavorite()
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors =
                                        ButtonDefaults.buttonColors(
                                            containerColor =
                                                MaterialTheme.colorScheme.secondary,
                                        ),
                                ) {
                                    Icon(
                                        imageVector =
                                            Icons.AutoMirrored
                                                .Default.Backspace,
                                        contentDescription =
                                            stringResource(
                                                R.string
                                                    .remove_last_emoji_button_description,
                                            ),
                                        tint = MaterialTheme.colorScheme.onSecondary,
                                    )
                                }
                            }
                        }
                    }

                    Card(
                        modifier =
                            Modifier
                                .fillMaxWidth(),
                    ) {
                        SearchField(searchQuery) { searchViewModel.search(it) }

                        if (searchQuery.isNotEmpty() && searchResultsAreLoading) {
                            EmptySearchBox {
                                CircularProgressIndicator()
                            }
                        } else if (searchQuery.isEmpty()) {
                            EmptySearchBox {
                                Text(
                                    text = stringResource(R.string.no_search),
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(10.dp),
                                )
                            }
                        } else {
                            @OptIn(ExperimentalLayoutApi::class)
                            FlowRow(
                                Modifier.padding(bottom = 20.dp)
                                    .verticalScroll(rememberScrollState()),
                            ) {
                                searchResults.forEach {
                                    IconFavorite(
                                        it,
                                        onIconClick = {
                                            favoritesViewModel
                                                .appendToCurrentFavoriteEmojiCodes(
                                                    it.code,
                                                )
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    showSaveDialog.value = true
                }) {
                    Text(stringResource(R.string.save))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        favoritesViewModel.clearCurrentFavorite()
                        showCreateDialog.value = false
                        searchViewModel.search("")
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

@Composable
fun EmptySearchBox(content: @Composable () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}
