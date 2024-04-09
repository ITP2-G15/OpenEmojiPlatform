package com.platform.openemoji.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.platform.openemoji.R
import com.platform.openemoji.emoji.Emoji
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FavoriteMaker(
    favoritesViewModel: FavoritesViewModel,
    emoji: Emoji,
) {
    val currentFavorite by favoritesViewModel.currentFavorite.collectAsState()
    val localCurrentFavorite = currentFavorite

    Card(
        modifier =
            Modifier
                .fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        if (localCurrentFavorite == null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                    Modifier.padding(8.dp).clickable {
                        favoritesViewModel.appendToCurrentFavoriteEmojiSequence(
                            emoji.code,
                        )
                    },
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription =
                        stringResource(
                            R.string.start_sequence_icon_description,
                        ),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp).size(36.dp),
                )
                Text(
                    text = "${stringResource(R.string.start_sequence)} ${emoji.code}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        } else {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier =
                        Modifier.padding(8.dp),
                ) {
                    Text(
                        text = "${localCurrentFavorite.emojiSequence}",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier =
                        Modifier.padding(8.dp),
                ) {
                    Button(
                        onClick = {
                            favoritesViewModel.appendToCurrentFavoriteEmojiSequence(
                                emoji.code,
                            )
                        },
                        modifier = Modifier.padding(end = 8.dp),
                    ) {
                        Text("Add Emoji")
                    }

                    Button(
                        onClick = { favoritesViewModel.clearCurrentFavorite() },
                        modifier = Modifier.padding(end = 8.dp),
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = {
                            CoroutineScope(Dispatchers.Main).launch {
                                favoritesViewModel.addCurrentFavoriteToFavorites()
                            }
                        },
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}
