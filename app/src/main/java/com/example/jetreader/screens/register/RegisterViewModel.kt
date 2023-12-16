package com.example.jetreader.screens.register

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetreader.model.RUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    var loading: Boolean by mutableStateOf(false)
    private var auth: FirebaseAuth = Firebase.auth
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


    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        username: String,
        retry: () -> Unit,
        home: () -> Unit
    ) =
        viewModelScope.launch(Dispatchers.IO) {
            loading = true
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveUserToFirestore(email, password, username)
                            loading = false
                            home()
                            Log.d("AUTH", "createUserWithEmailAndPassword: Yay")
                        } else {
                            loading = false
                            retry()
                            Log.d("AUTH", "createUserWithEmailAndPassword: Nooo")
                        }
                    }
            } catch (exception: Exception) {
                retry()
                Log.d("FB AUTH", "createUserWithEmailAndPassword: $exception")
            }
            loading = false
        }

    private fun saveUserToFirestore(email: String, password: String, username: String) =
        viewModelScope.launch {
            val user = RUser(
                userId = auth.currentUser?.uid.toString(),
                email = email,
                username = username
            ).toMap()

            FirebaseFirestore.getInstance().collection("user")
                .add(user)
        }

    fun checkUserDetails(
        type: String
    ): Boolean {
        usernameError = username.trim().isEmpty() || username.trim().length < 6
        passwordError = password.trim().isEmpty() || password.trim().length < 6
        confirmPasswordError = confirmPassword.trim().isEmpty() || confirmPassword != password
        emailError = email.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
        return !(emailError || passwordError || usernameError || confirmPasswordError)
    }
}