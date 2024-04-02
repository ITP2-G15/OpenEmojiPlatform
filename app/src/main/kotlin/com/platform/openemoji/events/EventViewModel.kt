package com.platform.openemoji.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val EVENTS_IN_EVENT_LIST_SCREEN = 15

class EventViewModel(
    private val eventsRepository: EventsRepository,
) : ViewModel() {
    private val _events = MutableStateFlow<List<Event>?>(null)
    val events = _events.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _events.value = eventsRepository.getEvents(EVENTS_IN_EVENT_LIST_SCREEN)
        }
    }
}
