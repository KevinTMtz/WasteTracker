package com.bruwus.wastetracker.ui.learn.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.R

class HorizontalRecyclerViewAdapter (private val recyclerList: List<String>): RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalRecyclerViewHolder>() {
    private var clickListener: ClickListener? = null

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener{
        fun onItemClick(view: View, pos: Int)
    }

    inner class HorizontalRecyclerViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private val title: TextView = view.findViewById(R.id.cell_column_title)
        private val description: TextView = view.findViewById(R.id.cell_column_description)

        fun bind(text: String) {
            title.text = text
            description.text = "Description of ${text}"
        }

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            if(view != null) {
                clickListener?.onItemClick(view, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_column, parent, false)

        return HorizontalRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalRecyclerViewHolder, position: Int) {
        val text = recyclerList[position]

        holder.bind(text)
    }

    override fun getItemCount() = recyclerList.size

    fun getData(pos: Int): String {
        return recyclerList[pos]
    }
}