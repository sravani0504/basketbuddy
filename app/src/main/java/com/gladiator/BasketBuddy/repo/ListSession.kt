package com.gladiator.BasketBuddy.repo

import com.gladiator.BasketBuddy.model.ItemList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ListSession {

    private val _selectedList = MutableStateFlow<ItemList?>(null)
    val selectedList: StateFlow<ItemList?> = _selectedList

    fun setSelectedList(itemList: ItemList) {
        _selectedList.value = itemList
    }

    fun clear() {
        _selectedList.value = null
    }
}