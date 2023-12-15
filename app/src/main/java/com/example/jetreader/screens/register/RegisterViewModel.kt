package com.example.jetreader.screens.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetreader.model.RUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    var loading = MutableLiveData(false)
        private set
    private var auth: FirebaseAuth = Firebase.auth

    fun createUserWithEmailAndPassword(email: String, password: String, username: String) = viewModelScope.launch {
        if (!loading.value!!) {
            loading.value = true
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveUserToFirestore(email, password, username)
                            Log.d("AUTH", "createUserWithEmailAndPassword: Yay")
                        } else {
                            Log.d("AUTH", "createUserWithEmailAndPassword: Nooo")
                        }
                    }
            } catch (exception: Exception) {
                Log.d("FB AUTH", "createUserWithEmailAndPassword: $exception")
            }
            loading.value = false
        }
    }
    private fun saveUserToFirestore(email: String, password: String, username: String) = viewModelScope.launch {
        val user = RUser(
            userId = auth.currentUser?.uid.toString(),
            email = email,
            username = username
        ).toMap()

        FirebaseFirestore.getInstance().collection("user")
            .add(user)
    }
}