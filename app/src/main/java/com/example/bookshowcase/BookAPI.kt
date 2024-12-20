package com.example.bookshowcase

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface BookService {
    @Headers("apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhidXZoaGV0ZnR2aWJ2cGx5cnhmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzIwODk5NjAsImV4cCI6MjA0NzY2NTk2MH0.Mt4EGJR63oW6FJh7blJ6IgOvaBaCj4b78ogTc91uq_A")
    @GET("books")
    suspend fun getBooks(): List<Book>
}

class BookAPI {

    private val baseUrl = "https://xbuvhhetftvibvplyrxf.supabase.co/rest/v1/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val bookService: BookService by lazy {
        retrofit.create(BookService::class.java)
    }

    suspend fun fetchBooks(): List<Book>? {
        try {
            val response  = bookService.getBooks()
            return response
        } catch (e: Exception) {
            Log.d("BookAPI", e.message.toString())
        }
        return null
    }

}