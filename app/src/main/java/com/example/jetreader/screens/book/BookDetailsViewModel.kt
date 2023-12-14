package com.example.jetreader.screens.book

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetreader.model.volume.Book
import com.example.jetreader.repository.ReaderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(private val repository: ReaderRepository): ViewModel() {

    var loading: Boolean by mutableStateOf(true)
    var bookDetails: Book? by mutableStateOf(null)

    fun getBookDetails(id: String) = viewModelScope.launch {
        loading = true
        bookDetails = repository.getBookData(id).data
        Log.d("BOOK", "getBookDetails: ${id}")
        loading = false
    }

}