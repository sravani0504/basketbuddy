package com.gladiator.BasketBuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gladiator.BasketBuddy.model.SignUpUiState
import com.gladiator.BasketBuddy.model.User
import com.gladiator.BasketBuddy.repo.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


sealed interface SignUpAction {

    data class EmailChanged(val value: String) : SignUpAction
    data class UsernameChanged(val value: String) : SignUpAction
    data class PasswordChanged(val value: String) : SignUpAction
    data class Submit(val onSuccess: () -> Unit) : SignUpAction
}

class SignUpViewModel(private val repository: UserRepository= UserRepository()): ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState

    fun onAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.EmailChanged -> {
                _uiState.update {
                    val err = if (action.value.isBlank()) "Email required" else null
                    it.copy(email = action.value, emailError = err)
                }
            }

            is SignUpAction.UsernameChanged -> {
                _uiState.update {
                    val err = if (action.value.length < 3) "Username too short" else null
                    it.copy(username = action.value, usernameError = err)
                }
            }

            is SignUpAction.PasswordChanged -> {
                _uiState.update {
                    val err = if (action.value.length < 6) "Password must be 6+ chars" else null
                    it.copy(password = action.value, passwordError = err)
                }
            }
            is SignUpAction.Submit -> {

//                val state = _uiState.value
//
//                if (state.isFormValid) {
//                    action.onSuccess()
//                }
                signUp(action.onSuccess)

            }
        }

    }

    private fun signUp(onSuccess: () -> Unit) {

        val state = _uiState.value

        if (!state.isFormValid) return

        viewModelScope.launch {

            val user = User(
                userId = (1..100000).random(),
                username = state.username,
                email = state.email,
                password = state.password,
                groupCode = ""
            )

            val result = repository.registerUser(user)

            result.onSuccess {
                onSuccess()
            }

            result.onFailure {
                _uiState.update { it.copy(error = "Signup failed") }
            }
        }
    }
}