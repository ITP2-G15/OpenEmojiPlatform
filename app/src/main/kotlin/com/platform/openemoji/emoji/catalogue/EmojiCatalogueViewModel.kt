package com.platform.openemoji.emoji.catalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.EmojiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmojiCatalogueViewModel(private val repository: EmojiRepository) : ViewModel() {
    private val _overviewEmojisByCategory = MutableLiveData<Map<String, List<Emoji>>>()
    val overviewEmojisByCategory: LiveData<Map<String, List<Emoji>>>
        get() = _overviewEmojisByCategory

    private val _emojisOfCategory = MutableLiveData<Pair<String, List<Emoji>>>()
    val emojisOfCategory: LiveData<Pair<String, List<Emoji>>>
        get() = _emojisOfCategory

    private val _mostPopularEmojis = MutableLiveData<List<Emoji>>()
    val mostPopularEmojis: LiveData<List<Emoji>>
        get() = _mostPopularEmojis

    fun loadOverviewEmojisByCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            val overviewEmojis = repository.getOverviewEmojis(14)
            _overviewEmojisByCategory.postValue(overviewEmojis)
        }
    }

    fun loadEmojisOfCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val emojis = repository.getEmojisOfCategory(category)
            _emojisOfCategory.postValue(category to emojis)
        }
    }

    fun loadMostPopularEmojis(limit: Int = Int.MAX_VALUE) {
        viewModelScope.launch(Dispatchers.IO) {
            val mostPopular = repository.getPopularEmojis(limit)
            _mostPopularEmojis.postValue(mostPopular)
        }
    }
}
