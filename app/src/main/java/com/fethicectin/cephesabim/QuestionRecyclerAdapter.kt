package com.fethicectin.orderly.Utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fethicectin.cephesabi.CepHesabiModel
import com.fethicectin.cephesabim.R

class QuestionRecyclerAdapter(private var models:List<CepHesabiModel>?): RecyclerView.Adapter<QuestionRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        val listItem = itemView.findViewById<TextView>(R.id.listItem)
        val listItemContainer = itemView.findViewById<LinearLayout>(R.id.listItemContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_transaction_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return models!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val itemString = models!!.get(position).amount.toString() + " TL" + " (" + models!!.get(position).description + ")"
            holder.listItem.text = itemString
            if(models!!.get(position).addorsub == 1){
                holder.listItemContainer.setBackgroundColor(Color.parseColor("#03DAC5"))
            }else{
                holder.listItemContainer.setBackgroundColor(Color.parseColor("#E41456"))
            }
    }


}

