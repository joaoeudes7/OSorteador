package com.jedev.osorteador.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jedev.osorteador.R
import kotlinx.android.synthetic.main.item_word.view.*

class ItemWordAdapter(private var items: MutableList<String>, private val funcDelete: (x: String) -> Unit):
    androidx.recyclerview.widget.RecyclerView.Adapter<ItemWordAdapter.CustomViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_word, parent, false)

        return CustomViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)

        holder.btnDelete.setOnClickListener {
            funcDelete.invoke(item)
        }
    }

    fun setItems(oldItems: MutableList<String>) {
        items = oldItems
        notifyDataSetChanged()
    }

    class CustomViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        private val textItem: TextView = itemView.text!!
        val btnDelete = itemView.btn_delete!!

        fun bind(item: String) {
            textItem.text = item
        }
    }
}