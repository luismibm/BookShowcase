package com.example.bookshowcase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDAO {

    @Query("select * from book")
    fun getBooks():LiveData<List<Book>>

    @Insert
    fun addBook(book: Book)

    @Insert
    fun addBooks(book: Book)

    @Delete
    fun deleteBook(book: Book)

    @Query("DELETE FROM book")
    fun deleteBooks()

}