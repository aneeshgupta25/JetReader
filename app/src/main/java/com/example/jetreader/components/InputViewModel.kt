package com.example.jetreader.components

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor() : ViewModel() {

    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set
    var passwordVisible by mutableStateOf(false)
        private set
    var usernameError by mutableStateOf(false)
        private set
    var passwordError by mutableStateOf(false)
        private set
    var emailError by mutableStateOf(false)
        private set
    var confirmPasswordError by mutableStateOf(false)
        private set
    var inputFieldEnabled by mutableStateOf(true)

    fun updateUsername(input: String) {
        usernameError = false
        username = input
    }
    fun updatePassword(input: String) {
        passwordError = false
        password = input
    }
    fun updateEmail(input: String) {
        emailError = false
        email = input
    }
    fun updateConfirmPassword(input: String) {
        confirmPasswordError = false
        confirmPassword = input
    }
    fun togglePasswordVisible() { passwordVisible = !passwordVisible }
    fun disableInputField() { inputFieldEnabled = false }
    fun enableInputField() { inputFieldEnabled = true }

    fun checkUserDetails(
        type: String
    ): Boolean {
        when (type) {
            "login" -> {
                passwordError = password.trim().isEmpty() || password.trim().length < 6
                emailError = email.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
                return !(emailError || passwordError)
            }
            "register" -> {
                usernameError = username.trim().isEmpty() || username.trim().length < 6
                passwordError = password.trim().isEmpty() || password.trim().length < 6
                confirmPasswordError = confirmPassword.trim().isEmpty() || confirmPassword != password
                emailError = email.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
                return !(emailError || passwordError || usernameError || confirmPasswordError)
            }
        }
        return true
    }
}