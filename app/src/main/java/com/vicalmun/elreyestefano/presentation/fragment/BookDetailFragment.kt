package com.vicalmun.elreyestefano.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vicalmun.elreyestefano.R
import com.vicalmun.elreyestefano.databinding.FragmentBookDetailBinding
import com.vicalmun.elreyestefano.model.Book
import com.vicalmun.elreyestefano.model.ResourceState
import com.vicalmun.elreyestefano.presentation.viewmodel.BookViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class BookDetailFragment : Fragment() {

    private val binding: FragmentBookDetailBinding by lazy {
        FragmentBookDetailBinding.inflate(layoutInflater)
    }

    private val args: BookDetailFragmentArgs by navArgs()

    private val booksViewModel: BookViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        booksViewModel.fetchBook(args.id)
    }

    private fun initViewModel() {
        booksViewModel.getBookDetailLiveData().observe(viewLifecycleOwner) { state ->
            handleBookDetailState(state)
        }
    }

    private fun handleBookDetailState(state: ResourceState<Book>) {
        when (state) {
            is ResourceState.Loading -> {
                binding.pbBookDetail.visibility = View.VISIBLE
            }

            is ResourceState.Success -> {
                binding.pbBookDetail.visibility = View.GONE
                initUI(state.result)
            }

            is ResourceState.Error -> {
                binding.pbBookDetail.visibility = View.GONE
                showErrorDialog(state.error)
            }
        }
    }

    private fun initUI(book: Book) {
        binding.tvDetailBookTitle.text = book.Title
        binding.tvDetailBookYear.text = "Año de salida: ${book.Year}"
        binding.tvDetailBookPages.text = "Páginas: ${book.Pages}"
    }

    private fun showErrorDialog(error: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.error) // Recuerdo de que hay que meter las strings
            .setMessage(error)
            .setPositiveButton(R.string.action_ok, null)
            .setNegativeButton(R.string.action_retry) { dialog, witch ->
                booksViewModel.fetchBooks()
            }
            .show()
    }
}