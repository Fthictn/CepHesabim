package com.fethicectin.orderly.Utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fethicectin.cephesabi.CepHesabiModel
import com.fethicectin.cephesabim.R

class QuestionRecyclerAdapter(private var models:List<CepHesabiModel>?): RecyclerView.Adapter<QuestionRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        val listItem = itemView.findViewById<TextView>(R.id.listItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_transaction_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return models!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.listItem.text = models!!.get(position).amount.toString()
    }


}

