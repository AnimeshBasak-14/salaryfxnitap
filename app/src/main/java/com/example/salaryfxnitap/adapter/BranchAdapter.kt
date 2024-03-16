package com.example.salaryfxnitap.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salaryfxnitap.R

class BranchAdapter(private val branchList: List<String>) :
    RecyclerView.Adapter<BranchAdapter.BranchViewHolder>() {

    private var selectedBranch: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        return BranchViewHolder(view)
    }

    override fun onBindViewHolder(holder: BranchViewHolder, position: Int) {
        val branch = branchList[position]
        holder.bind(branch)

        // Set click listener for the item
        holder.itemView.setOnClickListener {
            selectedBranch = branch
            notifyDataSetChanged()
        }

        // Set item background based on selection
        if (branch == selectedBranch) {
            holder.itemView.setBackgroundColor(Color.WHITE)
        } else {
            holder.itemView.setBackgroundColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int {
        return branchList.size
    }

    fun getSelectedBranch(): String? {
        return selectedBranch
    }

    class BranchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val branchTextView: TextView = itemView.findViewById(R.id.branchTextView)

        fun bind(branch: String) {
            branchTextView.text = branch
        }
    }
}

