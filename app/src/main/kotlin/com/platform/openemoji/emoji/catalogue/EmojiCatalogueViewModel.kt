package com.platform.openemoji.emoji.catalogue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.EmojiRepository
import com.platform.openemoji.emoji.OVERVIEW
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmojiCatalogueViewModel(
    private val repository: EmojiRepository,
) : ViewModel() {
    private val _mostPopularEmojis = MutableStateFlow<List<Emoji>>(emptyList())
    val mostPopularEmojis = _mostPopularEmojis.asStateFlow()

    private val _categories = MutableStateFlow<List<String>?>(null)
    val categories = _categories.asStateFlow()

    private val _selectedCategory = MutableStateFlow(OVERVIEW)
    val selectedCategory = _selectedCategory.asStateFlow()

    // Overview categories or a single category, null if it's loading.
    private val _selectedCategoryEmojis =
        MutableStateFlow<Map<String, List<Emoji>>?>(null)
    val selectedCategoryEmojis = _selectedCategoryEmojis.asStateFlow()

    fun loadCatalogue() {
        // Only load once
        if (_categories.value != null || _selectedCategoryEmojis.value != null) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            // Load category names.
            _categories.value = listOf(OVERVIEW) + repository.getCategories()
            // Load initial overview category emojis.
            _selectedCategoryEmojis.value = repository.getOverviewEmojis()
        }
    }

    fun selectCategory(category: String) {
        // While emojis are loading.
        _selectedCategoryEmojis.value = null

        // Start loading emojis.
        viewModelScope.launch(Dispatchers.IO) {
            _selectedCategoryEmojis.value =
                when (category) {
                    OVERVIEW -> repository.getOverviewEmojis()
                    else -> repository.getEmojisOfCategory(category)
                }
        }

        _selectedCategory.value = category
    }

    fun loadMostPopularEmojis(limit: Int = Int.MAX_VALUE) {
        viewModelScope.launch(Dispatchers.IO) {
            _mostPopularEmojis.value = repository.getPopularEmojis(limit)
        }
    }
}
