package com.platform.openemoji.emoji.catalogue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.EmojiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmojiCatalogueViewModel(private val repository: EmojiRepository) : ViewModel() {
    private val _overviewEmojisByCategory =
        MutableStateFlow<Map<String, List<Emoji>>>(
            emptyMap(),
        )
    val overviewEmojisByCategory = _overviewEmojisByCategory.asStateFlow()

    private val _emojisOfCategory = MutableStateFlow<Pair<String, List<Emoji>>?>(null)
    val emojisOfCategory = _emojisOfCategory.asStateFlow()

    private val _mostPopularEmojis = MutableStateFlow<List<Emoji>>(emptyList())
    val mostPopularEmojis = _mostPopularEmojis.asStateFlow()

    fun loadOverviewEmojisByCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            val overviewEmojis = repository.getOverviewEmojis(14)
            _overviewEmojisByCategory.value = overviewEmojis
        }
    }

    fun loadEmojisOfCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val emojis = repository.getEmojisOfCategory(category)
            _emojisOfCategory.value = category to emojis
        }
    }

    fun loadMostPopularEmojis(limit: Int = Int.MAX_VALUE) {
        viewModelScope.launch(Dispatchers.IO) {
            _mostPopularEmojis.value = repository.getPopularEmojis(limit)
        }
    }
}
