package com.vicalmun.elreyestefano.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class BookResponse (
    @SerializedName("data") val books: List<Book>
)

@Keep
data class Book (
    val id: Long,
    val year: Long,
    val title: String,
    val handle: String,
    val publisher: String,
    val isbn: String,
    val pages: Long,
    val notes: List<String>,
    val createdAt: String,
    val villains: List<Villain>
)

@Keep
data class Villain (
    val name: String,
    val url: String
)
