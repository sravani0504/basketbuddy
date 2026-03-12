package com.gladiator.BasketBuddy.viewmodel

import androidx.lifecycle.ViewModel
import com.gladiator.BasketBuddy.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ItemDisplayViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    fun setItems(itemList: List<Item>) {
        _items.value = itemList
    }

    fun incrementQuantity(index: Int) {
        _items.value = _items.value.mapIndexed { i, item ->
            if (i == index) item.copy(quantity = item.quantity + 1) else item
        }
    }

    fun decrementQuantity(index: Int) {
        _items.value = _items.value.mapIndexed { i, item ->
            if (i == index && item.quantity > 1)
                item.copy(quantity = item.quantity - 1)
            else item
        }
    }
}
