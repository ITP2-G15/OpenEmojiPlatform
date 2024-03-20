package com.platform.openemoji.emoji.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.platform.openemoji.R

@Composable
fun EmojiCategoryCarousel(categoryViewModel: EmojiCategoryViewModel) {
    val selectedCategory by categoryViewModel.selectedCategory.observeAsState(
        stringResource(R.string.overview),
    )
    val categories by categoryViewModel.categories.observeAsState(
        initial = emptyList(),
    )
    EmojiCategoryCarousel(
        selectedCategory,
        categories,
    ) {
        categoryViewModel.selectCategory(it)
    }
}

@Composable
fun EmojiCategoryCarousel(
    selectedCategory: String,
    categories: List<String>,
    onSelectCategory: (String) -> Unit,
) {
    Column {
        LazyRow(
            modifier = Modifier.padding(vertical = 8.dp),
        ) {
            items(categories.size) { index ->
                val category = categories[index]
                val isSelected = selectedCategory == category
                Button(
                    onClick = {
                        onSelectCategory(category)
                    },
                    modifier =
                        Modifier.padding(
                            horizontal = 4.dp,
                        ),
                    contentPadding = PaddingValues(8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor =
                                if (isSelected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.secondary
                                },
                        ),
                ) {
                    Text(
                        category,
                        style = MaterialTheme.typography.labelMedium,
                        color =
                            if (isSelected) {
                                MaterialTheme.colorScheme.onPrimary
                            } else {
                                MaterialTheme.colorScheme.onSecondary
                            },
                    )
                }
            }
        }
        Box(
            modifier =
                Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.onBackground
                            .copy(alpha = 0.25f),
                    ),
        )
    }
}
