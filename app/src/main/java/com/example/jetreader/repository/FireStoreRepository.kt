package com.example.jetreader.repository

import com.example.jetreader.data.DataOrException
import com.example.jetreader.model.MBook
import com.google.firebase.auth.FirebaseAuth
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
                dataOrException.data = value?.toObjects(MBook::class.java)?.filter {
                    it.userId == FirebaseAuth.getInstance().currentUser!!.uid
                }
            }
//            dataOrException.data = Fi.addSnapshotListener { value, error ->
//
//            }
//            get().await().documents.map { documentSnapshot ->
//                documentSnapshot.toObject(MBook::class.java)!!
//            }
            if(!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false
        } catch (exception: Exception) {
            dataOrException.e = exception
        }
        return dataOrException
    }
}