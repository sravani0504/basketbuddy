package com.gladiator.BasketBuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gladiator.BasketBuddy.model.ItemList
import com.gladiator.BasketBuddy.repo.BasketRepository
import com.gladiator.BasketBuddy.repo.GroupSession
import com.gladiator.BasketBuddy.repo.ListSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AddListUiState(
    val listName: String = "",
    val selectedGroupName: String = "",
    val listNameError: String? = null,
    val message: String? = null
)

class AddListViewModel(
    private val repository: BasketRepository = BasketRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddListUiState())
    val uiState: StateFlow<AddListUiState> = _uiState

    init {
        val group = GroupSession.selectedGroup.value
        _uiState.update {
            it.copy(selectedGroupName = group?.groupName.orEmpty())
        }
    }

    fun onListNameChanged(value: String) {
        _uiState.update {
            it.copy(listName = value, listNameError = null, message = null)
        }
    }

    fun createList(onSuccess: () -> Unit) {
        val group = GroupSession.selectedGroup.value
        val state = _uiState.value
        val trimmedName = state.listName.trim()

        if (group == null) {
            _uiState.update { it.copy(message = "Select a group first") }
            return
        }

        if (trimmedName.isBlank()) {
            _uiState.update { it.copy(listNameError = "Enter list name") }
            return
        }

        val itemList = ItemList(
            listId = (100000..999999).random(),
            name = trimmedName,
            groupCode = group.groupCode
        )

        viewModelScope.launch {
            val result = repository.createList(itemList)

            result.onSuccess {
                ListSession.setSelectedList(itemList)
                _uiState.update { it.copy(message = null) }
                onSuccess()
            }

            result.onFailure {
                _uiState.update { current ->
                    current.copy(message = it.message ?: "Unable to create list")
                }
            }
        }
    }
}