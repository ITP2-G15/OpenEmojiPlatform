package com.platform.openemoji.emoji

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EmojiViewModel(private val repository: EmojiRepository) : ViewModel() {
    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> get() = _categories

    private val _overviewEmojisByCategory =
        MutableLiveData<Map<String, List<Emoji>>>()
    val overviewEmojisByCategory: LiveData<Map<String, List<Emoji>>> get() =
        _overviewEmojisByCategory

    private val _allEmojisByCategory = MutableLiveData<Map<String, List<Emoji>>>()
    val allEmojisByCategory: LiveData<Map<String, List<Emoji>>> get() =
        _allEmojisByCategory

    private val _emojisBySearch = MutableLiveData<List<Emoji>>()
    val emojisBySearch: LiveData<List<Emoji>> get() = _emojisBySearch

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            val categories = repository.getCategories()
            _categories.value = categories
        }
    }

    fun loadOverviewEmojisForCategories(category: String) {
        categories.forEach { category ->
            loadEmojisByCategory(category, 14, _overviewEmojisByCategory)
        }
    }

    fun loadAllEmojisbyCategory(category: String) {
        loadEmojisByCategory(category, null, _allEmojisByCategory)
    }

    private fun loadEmojisByCategory(
        category: String,
        limit: Int?,
        liveData: MutableLiveData<Map<String, List<Emoji>>>,
    ) {
        viewModelScope.launch {
            val emojis = repository.getEmojisByCategory(category, limit)
            val currentMap = liveData.value ?: mapOf()
            liveData.value = currentMap + (category to emojis)
        }
    }

    fun loadEmojisBySearch(search: String) {
        viewModelScope.launch {
            val emojis = repository.getEmojiBySearch(search)
            _emojisBySearch.value = emojis
        }
    }
}
