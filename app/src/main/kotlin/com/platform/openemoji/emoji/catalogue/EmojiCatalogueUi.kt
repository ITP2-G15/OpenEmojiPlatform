package com.platform.openemoji.emoji.catalogue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.catalogue.grid.EmojiGrid

@Composable
fun EmojiCatalogueUi(
    emojis: Map<String, List<Emoji>>,
    modifier: Modifier = Modifier,
    maxEmojisPerGrid: Int? = null,
    navController: NavController,
) {
    Column(
        modifier =
            modifier
                .then(Modifier.padding(horizontal = 16.dp)),
    ) {
        emojis.forEach { (category, emojis) ->
            Text(
                text = category,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.testTag("catalogueUiCategoryHeader"),
            )
            // Limit number of emojis displayed in each grid
            val emojisToDisplay =
                if (maxEmojisPerGrid == null) {
                    emojis
                } else {
                    emojis.take(maxEmojisPerGrid)
                }

            EmojiGrid(emojis = emojisToDisplay, navController = navController)
        }
    }
}
