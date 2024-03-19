package com.platform.openemoji.emoji.catalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmojiCatalogueViewModel(private val repository: EmojiRepository) : ViewModel() {
    private val _overviewEmojisByCategory =
        MutableLiveData<Map<String, List<Emoji>>>()
    val overviewEmojisByCategory: LiveData<Map<String, List<Emoji>>> get() =
        _overviewEmojisByCategory

    private val _emojisByCategory = MutableLiveData<Map<String, List<Emoji>>>()
    val emojisByCategory: LiveData<Map<String, List<Emoji>>> get() =
        _emojisByCategory

    fun loadOverviewEmojisForCategories(categories: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            val overviewEmojis = repository.getOverviewEmojis(categories, 14)
            _overviewEmojisByCategory.postValue(overviewEmojis)
        }
    }

    fun loadEmojisByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val emojis = repository.getEmojisByCategory(category)
            _emojisByCategory.postValue(mapOf(category to emojis))
        }
    }
}
