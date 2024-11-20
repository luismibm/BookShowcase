package com.example.bookshowcase

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BookService {
    @GET("rest/v1/books")
    suspend fun getBooks(): Response<List<Book>>
}

class BookAPI {

    private val baseUrl = "https://xbuvhhetftvibvplyrxf.supabase.co/rest/v1/books?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhidXZoaGV0ZnR2aWJ2cGx5cnhmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzIwODk5NjAsImV4cCI6MjA0NzY2NTk2MH0.Mt4EGJR63oW6FJh7blJ6IgOvaBaCj4b78ogTc91uq_A"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val bookService: BookService by lazy {
        retrofit.create(BookService::class.java)
    }

    suspend fun fetchBooks(): List<Book>? {
        return try {
            val response = bookService.getBooks()
            if (response.isSuccessful) {
                response.body()
            } else {
                println("ERROR: Getting books")
                null
            }
        } catch (e: Exception) {
            println("ERROR: Realizar solicitud")
            null
        }
    }

}