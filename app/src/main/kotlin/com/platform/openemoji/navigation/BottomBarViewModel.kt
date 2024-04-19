package com.platform.openemoji.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

data class Tab(
    val orderValue: Int,
    val route: String,
)

/**
 * ViewModel for the bottom bar.
 * Holds the current and previous selected tab
 * and maps to order and route values.
 * NOTE: Can add additional list of tabs if needed. Can be useful to loop through
 * instead of manually creating tabs later.
 */
class BottomBarViewModel : ViewModel() {
    // Holds and maps current tab to its order value and route.
    private val currentSelectedTab = MutableStateFlow<Tab?>(null)
    val currentOrderValue = currentSelectedTab.asStateFlow().map { it?.orderValue }
    val currentRoute = currentSelectedTab.asStateFlow().map { it?.route }

    // Holds and maps previous tab to tis order value and route.
    private val previousSelectedTab = MutableStateFlow<Tab?>(null)
    val previousOrderValue = previousSelectedTab.asStateFlow().map { it?.orderValue }
    val previousRoute = previousSelectedTab.asStateFlow().map { it?.route }

    // Updates the previous state and the current state of the selected tab.
    fun selectTab(
        screen: String,
        orderValue: Int,
    ) {
        previousSelectedTab.value = currentSelectedTab.value
        currentSelectedTab.value = Tab(orderValue, screen)
    }

    // Resets the previous state and the current state of the selected tab.
    // NOTE: This is needed in order to properly transition to elements in each tab
    // that does not count as a tab element. As they transition differently. Therefore,
    // we need to reset the previous tab to null after transition has been complete.
    fun resetPreviousTab() {
        previousSelectedTab.value = null
    }
}
