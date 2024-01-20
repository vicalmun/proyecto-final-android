package com.vicalmun.elreyestefano.domain

import com.vicalmun.elreyestefano.model.Book

interface BookRepository {

    suspend fun getBooks(): List<Book>

    suspend fun getBook(id: Int): Book
}