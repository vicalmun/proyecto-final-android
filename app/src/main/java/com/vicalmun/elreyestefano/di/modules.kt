package com.vicalmun.elreyestefano.di
import com.vicalmun.elreyestefano.data.book.BookDataImpl
import com.vicalmun.elreyestefano.data.book.remote.BookRemoteDataSource
import com.vicalmun.elreyestefano.data.remote.LibraryService
import com.vicalmun.elreyestefano.data.remote.api.Client
import com.vicalmun.elreyestefano.domain.BookRepository
import com.vicalmun.elreyestefano.presentation.viewmodel.BookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val baseModule = module {
    // es un singleton
    single<LibraryService> {
        Client.retrofit.create(LibraryService::class.java)
    }
}

val bookModule = module {

    factory { BookRemoteDataSource(get()) }

    // repositorio
    factory<BookRepository> {
        BookDataImpl(get())
    }

    // hay que meterle el repo
    viewModel { BookViewModel(get()) }
}