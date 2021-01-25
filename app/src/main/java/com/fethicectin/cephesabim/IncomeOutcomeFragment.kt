package com.fethicectin.cephesabim

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fethicectin.cephesabi.CepHesabiModel
import com.fethicectin.orderly.Utils.QuestionRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_income_outcome.*

class IncomeOutcomeFragment : Fragment() {
    val db by lazy { DatabaseHelper(context!!)  }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_income_outcome, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var addButton = view?.findViewById<Button>(R.id.addToPocket)
        var subButton = view?.findViewById<Button>(R.id.subFromPocket)
        var amountInput = view?.findViewById<EditText>(R.id.amountInput)

        addButton?.setOnClickListener {
           var model = CepHesabiModel()
           model.amount = amountInput?.text.toString().toInt()
           model.addorsub = 1
           var rowId = db.insertData(model)
           if(rowId > -1){
               Toast.makeText(context!!,"Kayıt id : $rowId",Toast.LENGTH_SHORT).show()
           }else{
               Toast.makeText(context!!,"Hata!",Toast.LENGTH_SHORT).show()
           }
           amountInput?.text = null
        }

        subButton?.setOnClickListener {
           var model = CepHesabiModel()
           model.amount = amountInput?.text.toString().toInt()
           model.addorsub = 0
           var rowId = db.insertData(model)
           if(rowId > -1){
               Toast.makeText(context!!,"Kayıt id : $rowId",Toast.LENGTH_SHORT).show()
           }else{
               Toast.makeText(context!!,"Hata!",Toast.LENGTH_SHORT).show()
           }
           amountInput?.text = null

        }
        var modelList = db.retrieveData()
        Log.d("***LİSTEDDATAPRINT***",modelList.get(0).toString())
        transactionRecyclerView.layoutManager = LinearLayoutManager(context)
        transactionRecyclerView.adapter = QuestionRecyclerAdapter(modelList)
    }
}