package com.vicalmun.elreyestefano.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


import com.vicalmun.elreyestefano.databinding.RowBookItemBinding
import com.vicalmun.elreyestefano.model.Book

class BookListAdapter : RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {

        private var bookList: List<Book> = emptyList()

        var onClickListener: (Book) -> Unit = {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
            val binding = RowBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return BookListViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return bookList.size
        }

        override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
            val item = bookList[position]

            holder.rootView.setOnClickListener {
                onClickListener.invoke(item)
            }

            holder.nameTextView.text = item.Title
            holder.releasedDateTextView.text = item.Year.toString()

        }

        @SuppressLint("NotifyDataSetChanged")
        fun submitList(list: List<Book>) {
            bookList = list
            notifyDataSetChanged()
        }

        inner class BookListViewHolder(binding: RowBookItemBinding) : RecyclerView.ViewHolder(binding.root) {
            val rootView = binding.root
            val nameTextView = binding.tvBookName
            val releasedDateTextView = binding.tvBookReleased
        }
}