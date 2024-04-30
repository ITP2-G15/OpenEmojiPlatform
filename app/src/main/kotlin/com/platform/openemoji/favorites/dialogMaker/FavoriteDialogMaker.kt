package com.platform.openemoji.favorites.dialogMaker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.platform.openemoji.R
import com.platform.openemoji.favorites.FavoritesViewModel
import com.platform.openemoji.search.SearchViewModel

@Composable
fun FavoriteDialogMaker(
    favoritesViewModel: FavoritesViewModel,
    searchViewModel: SearchViewModel,
    showCreateDialog: MutableState<Boolean>,
) {
    val currentFavorite by favoritesViewModel.currentFavorite.collectAsState()
    val localCurrentFavorite = currentFavorite

    val searchQuery by searchViewModel.searchQuery.collectAsState()
    val searchResults by searchViewModel.searchResults
        .collectAsState(emptyList())

    val searchResultsAreLoading by searchViewModel.searchResultsAreLoading
        .collectAsState()

    if (showCreateDialog.value) {
        AlertDialog(
            onDismissRequest = { showCreateDialog.value = false },
            title = { Text(text = stringResource(R.string.create_sequence)) },
            text = {
                Column {
                    Card(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                    ) {
                        Text(
                            text = (
                                localCurrentFavorite
                                    ?.emojiCodes
                                    ?.joinToString("")
                                    ?: ""
                            ),
                            style = MaterialTheme.typography.displaySmall,
                            modifier = Modifier.padding(start = 8.dp),
                        )
                    }
                    Card(
                        modifier =
                            Modifier
                                .fillMaxWidth(),
                    ) {
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
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
