package com.example.bookshowcase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Book (

    @PrimaryKey
    val id: Int,
    val name: String,
    val author: String,
    val photo: String,
    val publisher: String,
    val pages: Int,
    val description: String

) : Serializable