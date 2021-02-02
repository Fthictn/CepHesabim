package com.fethicectin.cephesabim

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fethicectin.cephesabi.CepHesabiModel
import com.fethicectin.orderly.Utils.QuestionRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_income_outcome.*

class IncomeOutcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_income_outcome, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val db = DatabaseHelper(context!!)
        val addButton = view?.findViewById<Button>(R.id.addToPocket)
        val subButton = view?.findViewById<Button>(R.id.subFromPocket)
        val amountInput = view?.findViewById<EditText>(R.id.amountInput)
        val descriptionInput = view?.findViewById<EditText>(R.id.descriptionInput)
        val sumTextView = view?.findViewById<TextView>(R.id.sum)
        var sum = 0
        var sumText = ""
        addButton?.setOnClickListener {
           val model = CepHesabiModel()
           model.amount = amountInput?.text.toString().toInt()
           model.addorsub = 1
           model.description = descriptionInput?.text.toString()
           val rowId = db.insertData(model)
           if(rowId > -1){
               Toast.makeText(context!!,"Kayıt id : $rowId",Toast.LENGTH_SHORT).show()
           }else{
               Toast.makeText(context!!,"Hata!",Toast.LENGTH_SHORT).show()
           }
           amountInput?.text = null
           descriptionInput?.text = null
           val modelList = db.retrieveData()
           transactionRecyclerView.layoutManager = LinearLayoutManager(context)
           transactionRecyclerView.adapter = QuestionRecyclerAdapter(modelList)

            for(listElement in modelList){
                if(listElement.addorsub == 1){
                    sum += listElement.amount!!
                }else if(listElement.addorsub == 0){
                    sum -= listElement.amount!!
                }
            }
           sumText = sum.toString() + " TL"
           sumTextView!!.text = sumText
        }

        subButton?.setOnClickListener {
           val model = CepHesabiModel()
           model.amount = amountInput?.text.toString().toInt()
           model.addorsub = 0
           model.description = descriptionInput?.text.toString()
           val rowId = db.insertData(model)
           if(rowId > -1){
               Toast.makeText(context!!,"Kayıt id : $rowId",Toast.LENGTH_SHORT).show()
           }else{
               Toast.makeText(context!!,"Hata!",Toast.LENGTH_SHORT).show()
           }
           amountInput?.text = null
           descriptionInput?.text = null
           val modelList = db.retrieveData()
           transactionRecyclerView.layoutManager = LinearLayoutManager(context)
           transactionRecyclerView.adapter = QuestionRecyclerAdapter(modelList)

           for(listElement in modelList){
               if(listElement.addorsub == 1){
                   sum += listElement.amount!!
               }else if(listElement.addorsub == 0){
                   sum -= listElement.amount!!
               }
           }
           sumText = sum.toString() + " TL"
           sumTextView!!.text = sumText
        }

    }
}