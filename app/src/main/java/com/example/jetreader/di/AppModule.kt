package com.example.jetreader.di

import com.example.jetreader.network.BooksApi
import com.example.jetreader.repository.FireStoreRepository
import com.example.jetreader.repository.ReaderRepository
import com.example.jetreader.utils.AppConstants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFireStoreRepository(): FireStoreRepository {
        return FireStoreRepository(queryBook = FirebaseFirestore.getInstance().collection("books"))
    }

    @Singleton
    @Provides
    fun provideReaderRepository(api: BooksApi): ReaderRepository {
        return ReaderRepository(api)
    }

    @Singleton
    @Provides
    fun provideBooksApi(): BooksApi {

        val httpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApi::class.java)
    }

}