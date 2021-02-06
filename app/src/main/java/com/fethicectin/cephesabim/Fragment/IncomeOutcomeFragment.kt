package com.fethicectin.cephesabim.Fragment

import android.os.Bundle
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
import com.fethicectin.cephesabim.DbHelper.DatabaseHelper
import com.fethicectin.cephesabim.R
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
        val deleteAllButton = view?.findViewById<Button>(R.id.deleteAllButton)

        var modelList = db.retrieveData()
        transactionRecyclerView.layoutManager = LinearLayoutManager(context)
        transactionRecyclerView.adapter = QuestionRecyclerAdapter(modelList,context!!)

        addButton?.setOnClickListener {
            if(amountInput!!.text != null && amountInput.text.toString() != "" && descriptionInput!!.text != null && descriptionInput.text.toString() != "") {
               val model = CepHesabiModel()
               model.amount = amountInput.text.toString().toInt()
               model.description = descriptionInput.text.toString()
               val rowId = db.insertData(model)
               amountInput.text = null
               descriptionInput.text = null
               modelList = db.retrieveData()
               transactionRecyclerView.layoutManager = LinearLayoutManager(context)
               transactionRecyclerView.adapter = QuestionRecyclerAdapter(modelList,context!!)

               sumTextView!!.text = db.sumOfAmounts().toString()

           }else{
               Toast.makeText(context!!,"Lütfen zorunlu alanları doldurunuz!",Toast.LENGTH_SHORT).show()
           }
        }

        subButton?.setOnClickListener {
            if(amountInput!!.text != null && amountInput.text.toString() != "" && descriptionInput!!.text != null && descriptionInput.text.toString() != "") {
                val model = CepHesabiModel()
                model.amount = amountInput.text.toString().toInt().unaryMinus()
                model.description = descriptionInput.text.toString()
                val rowId = db.insertData(model)
                amountInput.text = null
                descriptionInput.text = null
                modelList = db.retrieveData()
                transactionRecyclerView.layoutManager = LinearLayoutManager(context)
                transactionRecyclerView.adapter = QuestionRecyclerAdapter(modelList, context!!)

                sumTextView!!.text = db.sumOfAmounts().toString()
            }else{
                Toast.makeText(context!!,"Lütfen zorunlu alanları doldurunuz!",Toast.LENGTH_SHORT).show()
            }
        }

        deleteAllButton?.setOnClickListener {
            db.deleteAllData()
            sumTextView!!.text = null
            modelList = db.retrieveData()
            transactionRecyclerView.layoutManager = LinearLayoutManager(context)
            transactionRecyclerView.adapter = QuestionRecyclerAdapter(modelList, context!!)
        }

    }
}