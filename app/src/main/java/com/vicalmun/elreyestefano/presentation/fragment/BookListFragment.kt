package com.vicalmun.elreyestefano.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vicalmun.elreyestefano.R

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.vicalmun.elreyestefano.model.ResourceState

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vicalmun.elreyestefano.databinding.FragmentBookListBinding
import com.vicalmun.elreyestefano.model.Book
import com.vicalmun.elreyestefano.presentation.adapter.BookListAdapter
import com.vicalmun.elreyestefano.presentation.viewmodel.BookViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class BookListFragment : Fragment() {

    private val binding: FragmentBookListBinding by lazy {
        FragmentBookListBinding.inflate(layoutInflater)
    }

    private val bookListAdapter = BookListAdapter()

    // Aquí solo decimos que el dueño de este fragmento sea el viewModel (creo)
    private val booksViewModel: BookViewModel by activityViewModel()
//     private val booksViewModel: BooksViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initUI()

        booksViewModel.fetchBooks()
    }

    private fun initViewModel() {

        // Si no usásemos inyección de dependencias tendríamos que crear un ViewModel a pelo y es morir

        booksViewModel.getBooksListLiveData().observe(viewLifecycleOwner) { state ->
            handleBookListState(state)
        }
    }

    private fun handleBookListState(state: ResourceState<List<Book>>) {
        when(state) {
            is ResourceState.Loading -> {
                binding.pbBookList.visibility = View.VISIBLE
            }
            is ResourceState.Success -> {
                binding.pbBookList.visibility = View.GONE
                bookListAdapter.submitList(state.result)
            }
            is ResourceState.Error -> {
                binding.pbBookList.visibility = View.GONE
                showErrorDialog(state.error)
            }
        }
    }

    private fun initUI() {
        binding.rvBookList.adapter = bookListAdapter
        binding.rvBookList.layoutManager = LinearLayoutManager(requireContext())

        bookListAdapter.onClickListener = { book ->

            findNavController().navigate(
                // esta clase contiene todas las acciones del navigation
                BookListFragmentDirections.actionBookListFragmentToBookDetailFragment(book.id.toInt())
            )
        }
    }

    private fun showErrorDialog(error: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("R.string.error")
            .setMessage(error)
            .setPositiveButton(R.string.action_ok, null)
            .setNegativeButton(R.string.action_retry) { dialog, witch ->
                booksViewModel.fetchBooks()
            }
            .show()
    }

}