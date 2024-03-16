package com.example.salaryfxnitap.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salaryfxnitap.R

class MonthAdapter(private val monthList: List<String>) :
    RecyclerView.Adapter<MonthAdapter.MonthViewHolder>() {

    private var selectedMonth: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_month, parent, false)
        return MonthViewHolder(view)
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {

        val month = monthList[position]
        holder.bind(month)

        // Set click listener for the item
        holder.itemView.setOnClickListener {
            selectedMonth = month
            notifyDataSetChanged()
        }

        // Set item background based on selection
        if (month == selectedMonth) {
            holder.itemView.setBackgroundColor(Color.WHITE)
        } else {
            holder.itemView.setBackgroundColor(Color.BLACK)
        }
    }


    override fun getItemCount(): Int {
        return monthList.size
    }

    fun getSelectedMonth(): String? {
        return selectedMonth
    }

    class MonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val monthTextView: TextView = itemView.findViewById(R.id.monthTextView)




        fun bind(month: String) {
            monthTextView.text = month
        }
    }
}

