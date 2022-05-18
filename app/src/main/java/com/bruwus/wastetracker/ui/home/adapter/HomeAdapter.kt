package com.bruwus.wastetracker.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bruwus.wastetracker.R

class HomeAdapter (private val homeList: List<String>): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private var clickListener: ClickListener? = null

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener{
        fun onItemClick(view: View, pos: Int)
    }

    inner class HomeViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private val title: TextView = view.findViewById(R.id.home_row_text_view)

        fun bind(text: String) {
            title.text = text
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_row, parent, false)

        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val text = homeList[position]

        holder.bind(text)
    }

    override fun getItemCount() = homeList.size

    fun getData(pos: Int): String {
        return homeList[pos]
    }
}