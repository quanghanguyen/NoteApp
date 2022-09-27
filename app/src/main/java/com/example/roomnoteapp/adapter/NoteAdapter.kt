package com.example.roomnoteapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomnoteapp.databinding.ItemsNoteBinding
import com.example.roomnoteapp.model.Notes

class NoteAdapter(private var list : List<Notes>) : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun addNewNotes(notes : List<Notes>) {
        list = notes
        notifyDataSetChanged()
    }

    class MyViewHolder(private val itemsNoteBinding: ItemsNoteBinding) : RecyclerView.ViewHolder(itemsNoteBinding.root) {
        fun bind (notes: Notes) {
            with(itemsNoteBinding) {
                tvTitle.text = notes.title
                tvDesc.text = notes.noteText
                tvDateTime.text = notes.dateTime
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val items = ItemsNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(items)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}