package com.fethicectin.cephesabim.Fragment

import android.graphics.Color.red
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fethicectin.cephesabim.DbHelper.DatabaseHelper
import com.fethicectin.cephesabim.R
import com.github.mikephil.charting.data.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_montly_result.*

class MontlyResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_montly_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val db = DatabaseHelper(context!!)
        val averageRemainingButton = view?.findViewById<FloatingActionButton>(R.id.averageRemaingButton)
        val averageOutCome = view?.findViewById<FloatingActionButton>(R.id.averageOutComeButton)
        val averageInCome = view?.findViewById<FloatingActionButton>(R.id.averageInComeButton)
        val containerFabButton = view?.findViewById<FloatingActionButton>(R.id.containerFabButton)
        var flag = true
        var amount = 0
        val entries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()

        labels.add("Ocak")
        labels.add("Şubat")
        labels.add("Mart")
        labels.add("Nisan")
        labels.add("Mayıs")
        labels.add("Haziran")
        labels.add("Temmuz")
        labels.add("Ağustos")
        labels.add("Eylül")
        labels.add("Ekim")
        labels.add("Kasım")
        labels.add("Aralık")

        var barDataSet:BarDataSet?

        var data:BarData?

        containerFabButton?.setOnClickListener {
            if(flag){
                averageRemainingButton!!.visibility= View.VISIBLE
                averageInCome!!.visibility= View.VISIBLE
                averageOutCome!!.visibility= View.VISIBLE
                flag = false
            }else{
                averageRemainingButton!!.visibility= View.GONE
                averageInCome!!.visibility= View.GONE
                averageOutCome!!.visibility= View.GONE

                flag = true
            }
        }

        averageRemainingButton?.setOnClickListener {
            entries.clear()
            averageRemainingButton.visibility = View.GONE
            averageInComeButton.visibility = View.GONE
            averageOutCome!!.visibility = View.GONE
            flag = true
            for (index in 0..11) {
                amount = db.getMounthlyAverages(labels[index])
                entries.add(BarEntry(amount.toFloat(), index))
            }

            barDataSet = BarDataSet(entries, "Tutar")

            data = BarData(getgraphLabels(), barDataSet)
            barchart.data = data
            barchart.setDescriptionColor(R.color.white)
            barchart.setDescription("Aylık net tutar")
            barchart.fitScreen()
            barDataSet!!.color = resources.getColor(R.color.colorAccent)
            barchart.notifyDataSetChanged()
            barchart.invalidate()
            barchart.animateY(3000)
        }

        averageOutCome?.setOnClickListener {
            entries.clear()
            var amountType = mutableListOf<Int>()
            averageRemainingButton!!.visibility = View.GONE
            averageInComeButton.visibility = View.GONE
            averageOutCome.visibility = View.GONE
            flag = true
            for (index in 0..11){
                amountType = db.getMountlyPlusNegate(labels[index])
                if(!amountType.isEmpty()){
                    amount = amountType[1]
                }else{
                    amount = 0
                }
                entries.add(BarEntry(amount.toFloat(),index))
            }

            barDataSet = BarDataSet(entries, "Tutar")

            data = BarData(getgraphLabels(), barDataSet)
            barchart.data = data
            barchart.setDescription("Aylık net gider")
            barchart.fitScreen()
            barDataSet!!.color = resources.getColor(R.color.colorAccent)
            barchart.notifyDataSetChanged()
            barchart.invalidate()
            barchart.animateY(3000)
        }

        averageInCome?.setOnClickListener {
            entries.clear()
            var amountType = mutableListOf<Int>()
            averageRemainingButton!!.visibility = View.GONE
            averageInComeButton.visibility = View.GONE
            averageOutCome!!.visibility = View.GONE
            flag = true

            for (index in 0..11){
                amountType = db.getMountlyPlusNegate(labels[index])
                if(!amountType.isEmpty()){
                    amount = amountType[0]
                }else{
                    amount = 0
                }
                entries.add(BarEntry(amount.toFloat(),index))
            }

            barDataSet = BarDataSet(entries, "Tutar")

            data = BarData(getgraphLabels(), barDataSet)
            barchart.data = data
            barchart.setDescription("Aylık net gelir")
            barchart.fitScreen()
            barDataSet!!.color = resources.getColor(R.color.colorAccent)
            barchart.notifyDataSetChanged()
            barchart.invalidate()
            barchart.animateY(3000)
        }

    }

    fun getgraphLabels():ArrayList<String>{
        val labels = ArrayList<String>()
        labels.add("Ocak")
        labels.add("Şubat")
        labels.add("Mart")
        labels.add("Nisan")
        labels.add("Mayıs")
        labels.add("Haziran")
        labels.add("Temmuz")
        labels.add("Ağustos")
        labels.add("Eylül")
        labels.add("Ekim")
        labels.add("Kasım")
        labels.add("Aralık")

        return labels
    }

}