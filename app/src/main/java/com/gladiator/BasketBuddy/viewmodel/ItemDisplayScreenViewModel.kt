package com.gladiator.BasketBuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gladiator.BasketBuddy.model.Item
import com.gladiator.BasketBuddy.repo.BasketRepository
import com.gladiator.BasketBuddy.repo.ListSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemDisplayViewModel(
    private val repository: BasketRepository = BasketRepository()
) : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    private val _selectedListName = MutableStateFlow("")
    val selectedListName: StateFlow<String> = _selectedListName.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    fun setItems(itemList: List<Item>) {
        _items.value = itemList
    }

    fun loadItemsForSelectedList() {
        val selectedList = ListSession.selectedList.value

        if (selectedList == null) {
            _selectedListName.value = ""
            _message.value = "Select a list first"
            _items.value = emptyList()
            return
        }

        _selectedListName.value = selectedList.name

        viewModelScope.launch {
            val result = repository.getItemsForList(selectedList.listId)

            result.onSuccess {
                _items.value = it
                _message.value = null
            }

            result.onFailure {
                _items.value = emptyList()
                _message.value = it.message ?: "Unable to load items"
            }
        }
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

    fun saveChanges(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = repository.updateItems(_items.value)

            result.onSuccess {
                _message.value = "Changes saved"
                onSuccess()
            }

            result.onFailure {
                _message.value = it.message ?: "Unable to save items"
            }
        }
    }
}
