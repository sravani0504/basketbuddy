package com.gladiator.BasketBuddy.model


data class SignUpUiState(

    val email: String = "",
    val username: String = "",
    val password: String = "",

    val emailError: String? = null,
    val usernameError: String? = null,
    val passwordError: String? = null,

    val isLoading: Boolean = false,
    val error: String? = null
) {

    val isFormValid: Boolean
        get() =
            email.isNotBlank() &&
                    username.isNotBlank() &&
                    password.isNotBlank() &&
                    emailError == null &&
                    usernameError == null &&
                    passwordError == null
}