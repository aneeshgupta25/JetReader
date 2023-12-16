package com.example.jetreader.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
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
class SearchViewModel @Inject constructor(private val readerRepository: ReaderRepository) :
    ViewModel() {
    var searchResult: List<Book>? by mutableStateOf(listOf())
    var defaultList: List<Book>? by mutableStateOf(listOf())
    var searchLoading: Boolean by mutableStateOf(true)
    var searchBarVisible by mutableStateOf(false)
        private set
    var searchContentVisible by mutableStateOf(false)
        private set
    var searchText by mutableStateOf("")
        private set
    private var searchingState by mutableStateOf(false)

    init {
        viewModelScope.launch {
            defaultList = readerRepository.getVolume("comic").data
        }
    }

    fun toggleSearchBarVisibility() {
        searchBarVisible = !searchBarVisible
    }

    private fun setSearchContentVisibility() {
        if(searchContentVisible) return
        searchContentVisible = true
    }

    fun hideSearchContents() {
        searchBarVisible = false
        searchContentVisible = false
        clearSearchText()
    }

    fun updateSearchText(input: String) {
        searchText = input
    }

    fun clearSearchText() {
        searchText = ""
    }

    fun isSearchQueryValid(): Boolean? {
        if (searchingState) return null
        searchingState = true
        if (searchText.trim().isNullOrEmpty()) {
            searchingState = false
            return false
        }
        setSearchContentVisibility()
        fetchSearchResults()
        return true
    }

    private fun fetchSearchResults() = viewModelScope.launch {
        searchLoading = true
        searchResult = readerRepository.getVolume((searchText)).data
        searchLoading = false
        searchingState = false
    }
}