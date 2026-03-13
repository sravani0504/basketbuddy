package com.gladiator.BasketBuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gladiator.BasketBuddy.model.ItemList
import com.gladiator.BasketBuddy.repo.BasketRepository
import com.gladiator.BasketBuddy.repo.GroupSession
import com.gladiator.BasketBuddy.repo.ListSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BasketViewModel(
    private val repository: BasketRepository = BasketRepository()
) : ViewModel() {

    private var allLists: List<ItemList> = emptyList()
    private val _lists = MutableStateFlow<List<ItemList>>(emptyList())
    val lists: StateFlow<List<ItemList>> = _lists.asStateFlow()

    private val _selectedGroupName = MutableStateFlow("")
    val selectedGroupName: StateFlow<String> = _selectedGroupName.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    fun loadListsForSelectedGroup() {
        val group = GroupSession.selectedGroup.value

        if (group == null) {
            _message.value = "Select a group from collaborations first"
            _lists.value = emptyList()
            _selectedGroupName.value = ""
            return
        }

        _selectedGroupName.value = group.groupName

        viewModelScope.launch {
            val result = repository.getListsForGroup(group.groupCode)

            result.onSuccess {
                allLists = it
                _lists.value = it
                _message.value = null
            }

            result.onFailure {
                _lists.value = emptyList()
                _message.value = it.message ?: "Unable to load lists"
            }
        }
    }

    fun onSearch(query: String) {
        val searchText = query.trim()
        if (searchText.isBlank()) {
            _lists.value = allLists
            return
        }

        _lists.value = allLists.filter {
            it.name.contains(searchText, ignoreCase = true)
        }
    }

    fun onListSelected(itemList: ItemList) {
        ListSession.setSelectedList(itemList)
    }
}