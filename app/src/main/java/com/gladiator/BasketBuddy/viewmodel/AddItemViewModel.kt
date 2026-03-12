package com.gladiator.BasketBuddy.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// If you use Hilt, uncomment:
// @HiltViewModel
// class AddItemViewModel @Inject constructor(
//     private val repository: ItemRepository
// ) : ViewModel() {

//class AddItemViewModel : ViewModel() {
//
//    private val _uiState = MutableStateFlow(AddItemUiState())
//    val uiState: StateFlow<AddItemUiState> = _uiState.asStateFlow()
//
//    fun onNameChange(value: String) {
//        updateState(
//            _uiState.value.copy(
//                itemName = value,
//                nameError = if (value.isBlank()) "Name cannot be empty" else null
//            )
//        )
//    }
//
//    fun onDescriptionChange(value: String) {
//        updateState(_uiState.value.copy(itemDescription = value))
//    }
//
//    fun onQuantityChange(value: String) {
//        // Allow only digits
//        val filtered = value.filter { it.isDigit() }
//        val quantityErr = when {
//            filtered.isBlank() -> "Quantity is required"
//            filtered.length > 1 && filtered.startsWith('0') -> "Quantity cannot start with 0"
//            filtered.toIntOrNull()?.let { it <= 0 } == true -> "Quantity must be greater than 0"
//            else -> null
//        }
//        updateState(
//            _uiState.value.copy(
//                quantityText = filtered,
//                quantityError = quantityErr
//            )
//        )
//    }
//
//    fun onSaveClicked(onSaved: (Item) -> Unit) {
//        val state = _uiState.value
//
//        // Final validation
//        val nameErr = if (state.itemName.isBlank()) "Name cannot be empty" else null
//        val qtyInt = state.quantityText.toIntOrNull()
//        val qtyErr = when {
//            state.quantityText.isBlank() -> "Quantity is required"
//            qtyInt == null || qtyInt <= 0 -> "Enter a valid quantity"
//            else -> null
//        }
//
//        val hasError = nameErr != null || qtyErr != null
//        if (hasError) {
//            updateState(state.copy(nameError = nameErr, quantityError = qtyErr))
//            return
//        }
//
//        // Simulate save (replace with repository call)
//        viewModelScope.launch {
//            updateState(state.copy(isSaving = true))
//            // Example: repository.addItem(Item(...))
//            delay(300) // simulate I/O
//            val newItem = Item(
//                name = state.itemName.trim(),
//                description = state.itemDescription.trim(),
//                quantity = qtyInt!!
//            )
//            onSaved(newItem)
//            updateState(
//                _uiState.value.copy(
//                    isSaving = false,
//                    saveSuccess = true
//                )
//            )
//        }
//    }
//
//    private fun updateState(newState: AddItemUiState) {
//        val canSave = newState.nameError == null &&
//                newState.quantityError == null &&
//                newState.itemName.isNotBlank() &&
//                newState.quantityText.isNotBlank() &&
//                !newState.isSaving
//
//        _uiState.value = newState.copy(canSave = canSave)
//    }
//}

