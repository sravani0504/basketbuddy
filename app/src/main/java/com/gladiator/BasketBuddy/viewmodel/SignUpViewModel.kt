package com.gladiator.BasketBuddy.viewmodel

import androidx.lifecycle.ViewModel
import com.gladiator.BasketBuddy.model.SignUpUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


sealed interface SignUpAction {

    data class EmailChanged(val value: String) : SignUpAction
    data class UsernameChanged(val value: String) : SignUpAction
    data class PasswordChanged(val value: String) : SignUpAction

    data class Submit(val onSuccess: () -> Unit) : SignUpAction
}

class SignUpViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState

    fun onAction(action: SignUpAction) {

        when (action) {

            is SignUpAction.EmailChanged -> {
                _uiState.update {
                    it.copy(email = action.value)
                }
            }

            is SignUpAction.UsernameChanged -> {
                _uiState.update {
                    it.copy(username = action.value)
                }
            }

            is SignUpAction.PasswordChanged -> {
                _uiState.update {
                    it.copy(password = action.value)
                }
            }

            is SignUpAction.Submit -> {

                val state = _uiState.value

                if (state.isFormValid) {
                    action.onSuccess()
                }

            }
        }
    }
}