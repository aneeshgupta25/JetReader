package com.example.jetreader.network

import com.example.jetreader.model.volume.Book
import com.example.jetreader.model.volume.Volume
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface BooksApi {

    @GET("volumes")
    suspend fun getVolumeData(
        @Query("q") q: String
    ): Volume?

    @GET("volumes/{book_id}")
    suspend fun getBookData(
        @Path("book_id") bookId: String
    ): Book?

}