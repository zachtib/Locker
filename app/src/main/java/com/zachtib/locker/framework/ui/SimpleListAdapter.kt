package com.zachtib.locker.framework.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zachtib.locker.R
import kotlinx.android.synthetic.main.item_text_label.view.*

class SimpleListAdapter<T>(callback: DiffUtil.ItemCallback<T>, private val getLabel: (T) -> String) :
    ListAdapter<T, SimpleListAdapter.SimpleViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        return SimpleViewHolder(parent.inflate(R.layout.item_text_label))
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        val item = getItem(position)
        val label = getLabel(item)
        holder.setText(label)
    }


    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setText(text: String) {
            itemView.label.text = text
        }
    }
}