package com.vicalmun.elreyestefano.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class BookResponse (
    @SerializedName("data") val books: List<Book>
)

data class BookDetailResponse (
    @SerializedName("data") val book: Book
)

@Keep
data class Book (
    val id: Int,
    val Year: Int,
    val Title: String,
    val handle: String,
    val publisher: String,
    val isbn: String,
    val Pages: Int,
    val Notes: List<String>,
    val createdAt: String,
    val villains: List<Villain>
)

@Keep
data class Villain (
    val name: String,
    val url: String
)
