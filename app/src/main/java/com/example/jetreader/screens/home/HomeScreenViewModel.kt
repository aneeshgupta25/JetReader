package com.example.jetreader.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetreader.data.DataOrException
import com.example.jetreader.model.MBook
import com.example.jetreader.repository.FireStoreRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
) : ViewModel() {

    var booksFromCart: MutableState<DataOrException<List<MBook>, Boolean, Exception>> =
        mutableStateOf(DataOrException(listOf(), true, Exception("")))
        private set

    init {
        getBooksFromCart()
    }

    private fun getBooksFromCart() {
        viewModelScope.launch {
            booksFromCart.value.loading = true
            booksFromCart.value = fireStoreRepository.getAllBooksFromFireStore()
            if(booksFromCart.value.data != null)
                booksFromCart.value.loading = false
        }
    }

}