package com.example.jetreader.screens.categories


import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetreader.data.DataOrException
import com.example.jetreader.model.volume.Book
import com.example.jetreader.repository.ReaderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val readerRepository: ReaderRepository) :
    ViewModel() {

    var loading: Boolean by mutableStateOf(true)
    var data: List<Book>? by mutableStateOf(listOf())

    fun getCategoryBooks(query: String) {

        viewModelScope.launch {
            loading = true
            data = readerRepository.getVolume(query).data
            loading = false
        }
    }
}