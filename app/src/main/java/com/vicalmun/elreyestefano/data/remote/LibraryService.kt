package com.vicalmun.elreyestefano.data.remote

import com.vicalmun.elreyestefano.model.Book
import com.vicalmun.elreyestefano.model.BookDetailResponse
import com.vicalmun.elreyestefano.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LibraryService {

    @GET("books")
    suspend fun getBooks(): BookResponse

    @GET("book/{id}")
    suspend fun getBook(@Path("id") id: Int): BookDetailResponse
}