package com.platform.openemoji.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.openemoji.emoji.Emoji
import com.platform.openemoji.emoji.EmojiRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmojiSearchViewModel(
    private val emojiRepository: EmojiRepository,
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Emoji>>(emptyList())
    val searchResults = _searchResults.asStateFlow()

    // Cancel previous search job when new search starts before the previous was finished.
    private var searchResultJob: Job? = null

    fun search(query: String) {
        _searchQuery.value = query
        searchResultJob?.cancel()
        searchResultJob =
            viewModelScope.launch {
                _searchResults.value =
                    emojiRepository.searchEmojis(query)
            }
    }
}
