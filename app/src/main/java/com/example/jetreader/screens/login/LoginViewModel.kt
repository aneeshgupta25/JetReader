package com.example.jetreader.screens.login

import android.util.Log
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

    var loading = MutableLiveData(false)
        private set
    private var auth: FirebaseAuth = Firebase.auth

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("AUTH", "signInWithEmailAndPassword: Yay")
                        home()
                    } else {
                        Log.d("AUTH", "signInWithEmailAndPassword: Nooo")
                    }
                }
        } catch (exception: Exception) {
            Log.d("FB AUTH", "signInWithEmailAndPassword: $exception")
        }
    }
}