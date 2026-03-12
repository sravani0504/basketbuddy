package com.gladiator.BasketBuddy.viewmodel

import androidx.lifecycle.ViewModel
import com.gladiator.BasketBuddy.model.Group
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CollaborationViewModel: ViewModel() {

    private val _groups= MutableStateFlow<List<Group>>(emptyList())

    val groups: StateFlow<List<Group>> = _groups

    fun setGroups(groupList: List<Group>){
        _groups.value=groupList
    }
}