package com.vicalmun.elreyestefano.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicalmun.elreyestefano.domain.BookRepository
import com.vicalmun.elreyestefano.model.Book
import com.vicalmun.elreyestefano.model.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookViewModel (private val bookRepository: BookRepository) : ViewModel(){

    private val bookMutableLiveData = MutableLiveData<ResourceState<List<Book>>>()

    private val bookDetailMutableLiveData = MutableLiveData<ResourceState<Book>>()



    fun getBooksListLiveData(): LiveData<ResourceState<List<Book>>> {
        return bookMutableLiveData
    }

    fun getBookDetailLiveData(): LiveData<ResourceState<Book>> {
        return bookDetailMutableLiveData
    }

    fun fetchBooks() {
        bookMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = bookRepository.getBooks()

                withContext(Dispatchers.Main) {
                    bookMutableLiveData.value = ResourceState.Success(data)

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    bookMutableLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }


    fun fetchBook(bookId: Int) {
        bookDetailMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = bookRepository.getBook(bookId)

                withContext(Dispatchers.Main) {
                    bookDetailMutableLiveData.value = ResourceState.Success(data)

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    bookDetailMutableLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }
}