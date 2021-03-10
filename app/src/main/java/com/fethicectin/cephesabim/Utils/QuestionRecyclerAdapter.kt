package com.fethicectin.orderly.Utils

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.fethicectin.cephesabi.CepHesabiModel
import com.fethicectin.cephesabim.DbHelper.DatabaseHelper
import com.fethicectin.cephesabim.R

class QuestionRecyclerAdapter(private var models:MutableList<CepHesabiModel>?, private val context: Context?): RecyclerView.Adapter<QuestionRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        val listItem = itemView.findViewById<TextView>(R.id.listItem)
        val listItemContainer = itemView.findViewById<RelativeLayout>(R.id.listItemContainer)
        val deleteButton = itemView.findViewById<Button>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_transaction_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return models!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val db = DatabaseHelper(context!!)
        val itemString = models!!.get(position).amount.toString() + " TL " +  models!!.get(position).description
        holder.listItem.text = itemString
        if(models!!.get(position).amount!! > 0.0){
            holder.listItemContainer.setBackgroundColor(Color.parseColor("#23EBC4"))
        }else{
            holder.listItemContainer.setBackgroundColor(Color.parseColor("#23A5EB"))
        }

        holder.deleteButton.setOnClickListener {
            //db.deleteData(models!!.get(position).id!!)
            db.deleteData(position)
            models!!.removeAt(position)
            notifyDataSetChanged()
        }

    }

}





