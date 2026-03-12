package com.gladiator.BasketBuddy.model

data class LoginUiState(
    val username: String ="",
    val password: String ="",
    val usernameError: String?=null,
    val passwordError:String?=null,
    val error: String?=null
) {
    val isFormValid: Boolean get()=username.isNotBlank() && password.isNotBlank() && usernameError==null && passwordError==null
}