package com.zachtib.locker.ui.notes

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zachtib.locker.R
import com.zachtib.locker.framework.ui.inflate
import com.zachtib.locker.models.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(initialItems: List<Note> = listOf()) :
    ListAdapter<Note, NoteAdapter.NoteViewHolder>(object : DiffUtil.ItemCallback<Note>() {
        override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
        override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id
    }) {

    init {
        this.submitList(initialItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(parent.inflate(R.layout.item_note))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = itemView.apply {
            noteNameTextView.text = note.title
        }
    }
}