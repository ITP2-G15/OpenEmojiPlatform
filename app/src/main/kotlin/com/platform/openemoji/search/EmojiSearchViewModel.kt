package com.platform.openemoji.search

import androidx.lifecycle.ViewModel
import com.platform.openemoji.emoji.EmojiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest

class EmojiSearchViewModel(
    private val emojiRepository: EmojiRepository,
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val searchResultsAreLoading = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchResults =
        searchQuery
            .debounce(250)
            .mapLatest {
                if (it.isNotEmpty()) {
                    searchResultsAreLoading.value = true
                    val searchResults = emojiRepository.searchEmojis(it)
                    searchResultsAreLoading.value = false
                    return@mapLatest searchResults
                } else {
                    searchResultsAreLoading.value = true
                    return@mapLatest emptyList()
                }
            }

    fun search(query: String) {
        _searchQuery.value = query
    }
}
