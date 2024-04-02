package com.platform.openemoji.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val NEWS_IN_NEWS_LIST_SCREEN = 15

class NewsViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {
    private val _latestNews = MutableStateFlow<List<News>?>(null)
    val latestNews = _latestNews.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _latestNews.value = newsRepository.getNews(NEWS_IN_NEWS_LIST_SCREEN)
        }
    }
}
