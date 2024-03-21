package com.platform.openemoji.emoji.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.openemoji.emoji.EmojiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmojiCategoryViewModel(
    private val repository: EmojiRepository,
) : ViewModel() {
    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>>
        get() = _categories

    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String>
        get() = _selectedCategory

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val categories = repository.getCategories()
            _categories.postValue(categories)
        }
    }

    fun selectCategory(category: String) {
        _selectedCategory.postValue(category)
    }
}
