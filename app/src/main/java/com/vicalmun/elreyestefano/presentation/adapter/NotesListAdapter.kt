package com.vicalmun.elreyestefano.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vicalmun.elreyestefano.databinding.RowBookItemBinding
import com.vicalmun.elreyestefano.databinding.RowNoteItemBinding
import com.vicalmun.elreyestefano.model.Book

class NotesListAdapter() : RecyclerView.Adapter<NotesListAdapter.NoteListViewHolder>() {

    private var noteList: List<String>  = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val binding = RowNoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        holder.noteTextView.text = noteList[position] // como es un RV de strings es el item en sí mismo
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<String>) {
        noteList = list
        notifyDataSetChanged()
    }

    inner class NoteListViewHolder(binding: RowNoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val rootView = binding.root
        val noteTextView = binding.tvNote
    }
}