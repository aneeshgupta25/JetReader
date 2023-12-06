package com.example.jetreader.components

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class InputViewModel: ViewModel() {

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

    fun updateUsername(input: String) { username = input }
    fun updatePassword(input: String) { password = input }
    fun updateEmail(input: String) { email = input }
    fun updateConfirmPassword(input: String) { confirmPassword = input }
    fun togglePasswordVisible() { passwordVisible = !passwordVisible }

    fun submitRegForm(): Boolean {
        usernameError = username.trim().isEmpty() || username.trim().length < 6
        passwordError = password.trim().isEmpty() || password.trim().length < 6
        confirmPasswordError = confirmPassword.trim().isEmpty() || confirmPassword != password
        emailError = email.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()

        return !(usernameError || passwordError || confirmPasswordError || emailError)
    }
}