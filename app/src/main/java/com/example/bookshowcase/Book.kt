package com.example.bookshowcase

import java.io.Serializable

data class Book (

    val id: Int,
    val name: String,
    val author: String,
    val photo: String,
    val publisher: String,
    val pages: Int,
    val description: String

) : Serializable