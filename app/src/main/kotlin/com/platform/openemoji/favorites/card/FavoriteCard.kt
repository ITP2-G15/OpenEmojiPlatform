package com.platform.openemoji.favorites.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.platform.openemoji.R
import com.platform.openemoji.favorites.Favorite
import com.platform.openemoji.favorites.FavoritesViewModel
import com.platform.openemoji.favorites.card.dialogs.FavoriteDeleteDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FavoriteCard(
    favoritesViewModel: FavoritesViewModel,
    favorite: Favorite,
) {
    val clipboardManager = LocalClipboardManager.current
    val showDeleteDialog = remember { mutableStateOf(false) }

    val context = LocalContext.current

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
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
                style = MaterialTheme.typography.titleLarge,
                modifier =
                    Modifier.padding(bottom = 8.dp).testTag("favoriteName"),
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = favorite.emojiCodes.joinToString(""),
                    style = MaterialTheme.typography.displaySmall,
                    modifier =
                        Modifier.padding(bottom = 8.dp).clickable {
                            clipboardManager.setText(
                                AnnotatedString(favorite.emojiCodes.joinToString("")),
                            )
                        }.testTag("emojiSequence"),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(
                        onClick = {
                            clipboardManager.setText(
                                AnnotatedString(favorite.emojiCodes.joinToString("")),
                            )
                        },
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    MaterialTheme.colorScheme.primary,
                            ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.testTag("copyButton"),
                    ) {
                        Icon(
                            Icons.Default.ContentCopy,
                            contentDescription =
                                stringResource(
                                    R.string.copy_sequence_icon_description,
                                ),
                            modifier = Modifier.size(18.dp),
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                        Text(
                            stringResource(R.string.copy),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(start = 4.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }

                    Button(
                        onClick = {
                            showDeleteDialog.value = true
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    MaterialTheme.colorScheme.error,
                            ),
                        modifier = Modifier.testTag("deleteButton"),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription =
                                stringResource(
                                    R.string.delete,
                                ),
                            tint = MaterialTheme.colorScheme.onError,
                        )
                    }
                }
            }
        }
    }

    FavoriteDeleteDialog(
        favorite = favorite,
        showDeleteDialog = showDeleteDialog,
        onDelete = {
            CoroutineScope(Dispatchers.Main).launch {
                favoritesViewModel.deleteFavorite(favorite)
            }
        },
    )
}
