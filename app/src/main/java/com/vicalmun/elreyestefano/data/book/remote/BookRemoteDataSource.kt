package com.vicalmun.elreyestefano.data.book.remote

import com.vicalmun.elreyestefano.data.remote.LibraryService
import com.vicalmun.elreyestefano.model.Book

class BookRemoteDataSource(private val libraryService: LibraryService) {

    suspend fun getBooks(): List<Book>{
        return libraryService.getBooks().books
    }

    suspend fun getBook(bookId: Int): Book {
        return libraryService.getBook(bookId).book
    }

}