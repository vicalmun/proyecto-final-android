package com.vicalmun.elreyestefano.data.book

import com.vicalmun.elreyestefano.data.book.remote.BookRemoteDataSource
import com.vicalmun.elreyestefano.domain.BookRepository
import com.vicalmun.elreyestefano.model.Book

class BookDataImpl(private val bookRemoteDataSource: BookRemoteDataSource) : BookRepository {
    override suspend fun getBooks(): List<Book> {
        return bookRemoteDataSource.getBooks()
    }

    override suspend fun getBook(id: Int): Book {
        return bookRemoteDataSource.getBook(id)
    }
}