package com.platform.openemoji.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import com.platform.openemoji.favorites.Favorite
import com.platform.openemoji.favorites.Sequence
import com.platform.openemoji.header.HeaderLogo

@Composable
fun FavoritesScreen(navController: NavController) {
    val favorites =
        listOf(
            Favorite(
                name = "Sample Name 1",
                emojiSequence =
                    "\uD83D\uDE00\uD83D\uDE03\uD83D\uDE04\uD83D\uDE01\uD83D" +
                        "\uDE00\uD83D\uDE03\uD83D\uDE04\uD83D\uDE01\uD83D\uDE00" +
                        "\uD83D\uDE03\uD83D\uDE04\uD83D\uDE01" +
                        "\uD83D\uDE00\uD83D\uDE03\uD83D\uDE04\uD83D\uDE01",
            ),
            Favorite(name = "Sample Name 2", emojiSequence = "🥳🤩🤪🤣"),
            Favorite(name = "Sample Name 3", emojiSequence = "😎😍😘😗"),
            Favorite(name = "Sample Name 4", emojiSequence = "🙂🙃😉😊"),
            Favorite(name = "Sample Name 5", emojiSequence = "🙂🙃😉😊"),
        )

    LazyColumn(
        modifier =
            Modifier
                .fillMaxWidth()
                .testTag("favouritesScreen"),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item { HeaderLogo() }

        // Loop through the list of favorites and display each one
        items(favorites) { favorite ->
            Sequence(favorite = favorite)
        }
    }
}
