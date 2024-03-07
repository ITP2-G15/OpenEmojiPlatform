package com.platform.openemoji.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        label = {
            Text("Search for emoji")
        },
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("searchTextField"),
        shape = RoundedCornerShape(12.dp),
    )
}
