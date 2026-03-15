package com.gladiator.BasketBuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gladiator.BasketBuddy.model.Item
import com.gladiator.BasketBuddy.repo.BasketRepository
import com.gladiator.BasketBuddy.repo.ListSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AddItemUiState(
    val itemName: String = "",
    val itemDescription: String = "",
    val quantityText: String = "",
    val selectedListName: String = "",
    val itemNameError: String? = null,
    val quantityError: String? = null,
    val message: String? = null
)

class AddItemViewModel(
    private val repository: BasketRepository = BasketRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddItemUiState())
    val uiState: StateFlow<AddItemUiState> = _uiState

    init {
        val selectedList = ListSession.selectedList.value
        _uiState.update {
            it.copy(selectedListName = selectedList?.name.orEmpty())
        }
    }

    fun onItemNameChanged(value: String) {
        _uiState.update {
            it.copy(itemName = value, itemNameError = null, message = null)
        }
    }

    fun onItemDescriptionChanged(value: String) {
        _uiState.update {
            it.copy(itemDescription = value, message = null)
        }
    }

    fun onQuantityChanged(value: String) {
        if (value.any { !it.isDigit() }) return

        _uiState.update {
            it.copy(quantityText = value, quantityError = null, message = null)
        }
    }

    fun saveItem(onSuccess: () -> Unit) {
        val selectedList = ListSession.selectedList.value
        val state = _uiState.value
        val quantity = state.quantityText.toIntOrNull()

        val itemNameError = if (state.itemName.trim().isBlank()) "Enter item name" else null
        val quantityError = if (quantity == null || quantity <= 0) "Enter valid quantity" else null

        if (selectedList == null) {
            _uiState.update { it.copy(message = "Select a list first") }
            return
        }

        if (itemNameError != null || quantityError != null) {
            _uiState.update {
                it.copy(itemNameError = itemNameError, quantityError = quantityError)
            }
            return
        }

        val item = Item(
            itemName = state.itemName.trim(),
            itemDescription = state.itemDescription.trim(),
            quantity = quantity?.toInt() ?: 0,
            listId = selectedList.listId
        )

//        viewModelScope.launch {
//            val result = repository.addItem(item)
//
//            result.onSuccess {
//                _uiState.value = AddItemUiState(selectedListName = selectedList.name)
//                onSuccess()
//            }
//
//            result.onFailure {
//                _uiState.update { current ->
//                    current.copy(message = it.message ?: "Unable to add item")
//                }
//            }
//        }

        viewModelScope.launch {

            val alreadyExists = repository.itemExists(
                selectedList.listId,
                state.itemName.trim()
            )

            if (alreadyExists) {
                _uiState.update {
                    it.copy(message = "Item already present")
                }
                return@launch
            }

            val result = repository.addItem(item)

            result.onSuccess {
                _uiState.value = AddItemUiState(selectedListName = selectedList.name)
                onSuccess()
            }

            result.onFailure {
                _uiState.update {
                    it.copy(message = it.message ?: "Unable to add item")
                }
            }
        }
    }
}

