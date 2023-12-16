package com.example.jetreader.screens.login

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
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var loading: Boolean by mutableStateOf(false)
    private var auth: FirebaseAuth = Firebase.auth

    var password by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var passwordVisible by mutableStateOf(false)
        private set
    var passwordError by mutableStateOf(false)
        private set
    var emailError by mutableStateOf(false)
        private set
    var inputFieldEnabled by mutableStateOf(true)
    fun updatePassword(input: String) {
        passwordError = false
        password = input
    }
    fun updateEmail(input: String) {
        emailError = false
        email = input
    }
    fun togglePasswordVisible() { passwordVisible = !passwordVisible }
    fun disableInputField() { inputFieldEnabled = false }
    fun enableInputField() { inputFieldEnabled = true }


    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        retry: () -> Unit,
        home: () -> Unit
    ) = viewModelScope.launch {
        Log.d("AUTHTH", "signInWithEmailAndPassword: $email")
        loading = true
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("AUTH", "signInWithEmailAndPassword: Yay")
                        loading = false
                        home()
                    } else {
                        loading = false
                        retry()
                        Log.d("AUTH", "signInWithEmailAndPassword: Nooo")
                    }
                }
                .addOnFailureListener {
                    loading = false
                }
        } catch (exception: Exception) {
            Log.d("FB AUTH", "signInWithEmailAndPassword: $exception")
        }
    }

    fun checkUserDetails(
        type: String
    ): Boolean {
        passwordError = password.trim().isEmpty() || password.trim().length < 6
        emailError = email.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
        return !(emailError || passwordError)
    }
}