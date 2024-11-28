package com.example.bookshowcase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class BookViewModel (val app: Application) : AndroidViewModel(app) {
    private val bookDatabase: BookDatabase = BookDatabase.getDatabase(app)

    val bookDAO: BookDAO = bookDatabase.bookDao
    val books: LiveData<List<Book>> = bookDAO.getBooks()

    fun reload() {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val api = BookAPI()
            bookDAO.deleteBooks()
            // bookDAO.addBooks(books)
        }
    }

}