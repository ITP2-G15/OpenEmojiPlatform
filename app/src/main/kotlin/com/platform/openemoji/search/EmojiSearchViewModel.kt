package com.platform.openemoji.search

import androidx.lifecycle.ViewModel
import com.amplitude.core.Amplitude
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

    /**
     * While the search query has changed and the search results haven't loaded
     * yet, this is true. It is also true when the search query is empty.
     */
    val searchResultsAreLoading = MutableStateFlow(false)

    /**
     * These search results are updated after the search query has changed.
     * There is debounce, so that the search results only update after a
     * small period of inactivity.
     */
    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    var searchResults =
        searchQuery
            .debounce(250)
            // mapLatest cancels old search jobs
            .mapLatest { query ->
                if (query.isNotEmpty()) {
                    searchResultsAreLoading.value = true
                    val searchResults = emojiRepository.searchEmojis(query)
                    searchResultsAreLoading.value = false
                    return@mapLatest searchResults
                } else {
                    // The search results need to be loading when the search query
                    // is empty because otherwise there is a small period after
                    // the user begins typing when it says there are no search
                    // results.
                    searchResultsAreLoading.value = true
                    return@mapLatest emptyList()
                }
            }

    fun search(query: String) {
        _searchQuery.value = query
    }

    // Starts collecting search analytics after calling useSearchAnalytics.
    // There's a long debounce to make it more likely that the user finished typing
    // before the search query is recorded.
    @OptIn(FlowPreview::class)
    suspend fun useSearchAnalytics(analytics: Amplitude) {
        searchQuery
            .debounce(2000)
            .collect { query ->
                if (query.isNotEmpty()) {
                    analytics.track(
                        "search",
                        mapOf("query" to query),
                    )
                }
            }
    }
}
