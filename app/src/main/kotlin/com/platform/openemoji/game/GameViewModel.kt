package com.platform.openemoji.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameRepository: GameRepository,
) : ViewModel() {
    // not sure if this is ever needed?
    private val _allLevels = MutableStateFlow<List<Level>>(emptyList())
    val allLevels = _allLevels.asStateFlow()

    private val _currentLevel = MutableStateFlow<Level?>(null)
    val currentLevel = _currentLevel.asStateFlow()

    private val _levelCounter = MutableStateFlow(0)
    val levelCounter = _levelCounter.asStateFlow()

    init {
        viewModelScope.launch {
            _allLevels.value = gameRepository.getAllLevels()
        }

        viewModelScope.launch {
            _levelCounter.value = gameRepository.getLevelCounter()
        }

        viewModelScope.launch {
            _currentLevel.value = gameRepository.getCurrentLevel()
        }
    }

    fun incrementLevelCounter() {
        viewModelScope.launch {
            gameRepository.incrementLevelCounter()
        }
    }
}
