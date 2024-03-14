package com.platform.openemoji.screens

import HeaderLogo
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.platform.openemoji.R
import com.platform.openemoji.emoji.catalogue.EmojiCatalogue
import com.platform.openemoji.emoji.catalogue.EmojiCatalogueUi
import com.platform.openemoji.events.Events

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.testTag("homeScreen"),
    ) {
        HeaderLogo()
        // Most Popular
        // Made using EmojiCatalogueUI with only one category: "Most Popular"
        EmojiCatalogueUi(
            emojis =
                mapOf(
                    stringResource(R.string.most_popular) to
                        EmojiCatalogue.get().mostPopularEmojis(14),
                ),
            navController = navController,
        )
        Events(navController)
    }
}
