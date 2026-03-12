package com.gladiator.BasketBuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gladiator.BasketBuddy.model.LoginUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


sealed interface LoginAction{
    data class UsernameChanged(val value:String) : LoginAction
    data class PasswordChanged(val value:String) : LoginAction
    data class Submit(val onSuccess:() -> Unit) : LoginAction
}


class LoginViewModel: ViewModel() {
    private val _uiState= MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> = _uiState

    fun onAction(action: LoginAction){
        when(action){
            is LoginAction.UsernameChanged -> {
                _uiState.update {
                    val err=validateUsername(action.value)
                    it.copy(username=action.value, usernameError = err, error = null)
                }
            }
            is LoginAction.PasswordChanged -> {
                _uiState.update {
                    val err=validatePassword(action.value)
                    it.copy(password =action.value, passwordError = err, error = null)
                }
            }
            is LoginAction.Submit -> submit(action.onSuccess)
        }
    }

    private fun submit(onSuccess: () -> Unit){
        val current=_uiState.value

        val usernameErr=validateUsername(current.username)
        val passwordErr=validatePassword(current.password)

        _uiState.update {
            it.copy(usernameError=usernameErr, passwordError = passwordErr, error = null)
        }

        if (usernameErr!=null || passwordErr!=null) return

        viewModelScope.launch {
            try {
                delay(1000)
            }catch (e: Throwable){
                _uiState.update { it.copy( error = "Something went wrong") }
            }
        }

    }

    private fun validateUsername(value: String): String?{
        if (value.isBlank()) return "Username is required"
        if (value.length<3) return "username must be atleast 3 characters"
        return null
    }

    private fun validatePassword(value: String): String?{
        if (value.isBlank()) return "Password is required"
        if (value.length<6) return "Password must be atleast 6 characters"
        return null
    }
}