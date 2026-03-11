package com.gladiator.BasketBuddy.model

data class LoginUiState(
    val username: String ="",
    val password: String ="",
    val isLoading: Boolean=false,
    val error: String?=null
) {
    val isFormValid: Boolean get()=username.isNotBlank() && password.isNotBlank()
}