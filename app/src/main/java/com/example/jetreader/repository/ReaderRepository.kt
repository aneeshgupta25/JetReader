package com.example.jetreader.repository

import android.util.Log
import com.example.jetreader.data.DataOrException
import com.example.jetreader.model.volume.Book
import com.example.jetreader.network.BooksApi
import javax.inject.Inject

class ReaderRepository @Inject constructor(private val api: BooksApi) {
    private var listOfBooksDataOrException = DataOrException<List<Book>?, Boolean, Exception>()
    private var bookDataOrException = DataOrException<Book?, Boolean, Exception>()
    suspend fun getVolume(
        query: String
    ): DataOrException<List<Book>?, Boolean, Exception> {
        try {
            listOfBooksDataOrException.loading = true
            listOfBooksDataOrException.data = api.getVolumeData(query)?.items
            if(!listOfBooksDataOrException.data.isNullOrEmpty())
                listOfBooksDataOrException.loading = false
        } catch(exception: Exception) {
            listOfBooksDataOrException.loading = false
            return DataOrException(e = exception)
        }
        return listOfBooksDataOrException
    }

    suspend fun getBookData(
        bookId: String
    ): DataOrException<Book?, Boolean, Exception> {
        try {
            bookDataOrException.loading = true
            bookDataOrException.data = api.getBookData(bookId = bookId)
            Log.d("THISISAPP", "getBookData: ${bookDataOrException.data}")
            if(bookDataOrException.data != null)
                bookDataOrException.loading = false
        } catch(exception: Exception) {
            bookDataOrException.loading = false
            return DataOrException(e = exception)
        }
        return bookDataOrException
    }

}