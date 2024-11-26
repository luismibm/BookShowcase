package com.example.bookshowcase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class BookGridAdapter(private val context: Context, private val books: List<Book>) : BaseAdapter() {

    override fun getCount(): Int {
        return books.size
    }

    override fun getItem(position: Int): Any {
        return books[position]
    }

    override fun getItemId(position: Int): Long {
        return books[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.book_item, parent, false)

        val book = books[position]
        val title = view.findViewById<TextView>(R.id.bookTitle)
        val image = view.findViewById<ImageView>(R.id.bookImage)

        title.text = book.name

        Glide.with(context)
            .load(book.photo)
            .into(image)

        return view

    }
}