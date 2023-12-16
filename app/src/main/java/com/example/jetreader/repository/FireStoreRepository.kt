package com.example.jetreader.repository

import android.util.Log
import com.example.jetreader.data.DataOrException
import com.example.jetreader.model.MBook
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreRepository @Inject constructor(
    private val queryBook: Query
) {
    suspend fun getAllBooksFromFireStore(): DataOrException<List<MBook>, Boolean, Exception> {
        val dataOrException = DataOrException<List<MBook>, Boolean, Exception>()
        try {
            dataOrException.loading = true
            queryBook.addSnapshotListener { value, error ->
                dataOrException.data = value?.toObjects(MBook::class.java)
            }
            if(!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false
        } catch (exception: FirebaseFirestoreException) {
            dataOrException.e = exception
        }
        return dataOrException
    }
}