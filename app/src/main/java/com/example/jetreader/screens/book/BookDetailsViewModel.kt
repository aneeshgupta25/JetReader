package com.example.jetreader.screens.book

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetreader.model.MBook
import com.example.jetreader.model.volume.Book
import com.example.jetreader.repository.ReaderRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(private val repository: ReaderRepository) :
    ViewModel() {

    var loading: Boolean by mutableStateOf(true)
    var bookDetails: Book? by mutableStateOf(null)
    private val db = FirebaseFirestore.getInstance()
    private val dbCollection = db.collection("books")

    fun getBookDetails(id: String) = viewModelScope.launch {
        loading = true
        bookDetails = repository.getBookData(id).data
        Log.d("BOOK", "getBookDetails: ${id}")
        loading = false
    }

    fun saveToCart(): Boolean {
        val book = MBook(
            book = bookDetails,
            userId = FirebaseAuth.getInstance().currentUser?.uid.toString(),
        )
        var flag = true
        if (!book.toString().isNullOrEmpty()) dbCollection.add(book)
            .addOnSuccessListener { documentRef ->
                val documentId = documentRef.id
                dbCollection.document(documentId)
                    .update(hashMapOf("id" to documentId) as Map<String, Any>)
            }.addOnCompleteListener { task ->
                Log.d("TAG", "saveToCart: ${task.toString()}")
            }.addOnFailureListener { exception ->
                flag = false
                Log.d("TAG", "saveToCart: ${exception.toString()}")
            }
        return flag
    }

    fun removeFromCart(
        fireStoreId: String
    ): Boolean {
        var flag = true
        dbCollection.document(fireStoreId).delete()
            .addOnCompleteListener {  task ->

            }
            .addOnFailureListener {
                flag = false
            }
        return flag
    }

}