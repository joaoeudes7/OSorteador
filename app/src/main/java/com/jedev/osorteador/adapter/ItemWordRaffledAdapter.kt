package com.jedev.osorteador.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jedev.osorteador.R
import kotlinx.android.synthetic.main.item_word_raffled.view.*

class ItemWordRaffledAdapter(private var items: MutableList<String>) : RecyclerView.Adapter<ItemWordRaffledAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_word_raffled, parent, false)

        return CustomViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    fun setItems(oldItems: MutableList<String>) {
        items = oldItems
        notifyDataSetChanged()
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val textItem: TextView = itemView.text!!

        fun bind(item: String) {
            textItem.text = item
        }
    }
}