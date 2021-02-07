package com.fethicectin.cephesabim.Fragment

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fethicectin.cephesabim.DbHelper.DatabaseHelper
import com.fethicectin.cephesabim.R
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
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
        val aaChartView = view?.findViewById<AAChartView>(R.id.aa_chart_view)
        var flag = true
        val mounthNames = arrayOf("Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık")
        var totalMounthlyAverage = arrayListOf<Int>()

        for(i in 0 until 11){
            totalMounthlyAverage.add(db.getMounthlyAverages(mounthNames[i]))
        }

        val chartRemainingModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Waterfall)
            .title("Aylara Göre Ortalama Geliriniz")
            .subtitle("")
            .backgroundColor("#FFFFFF")
            .dataLabelsEnabled(true)
            .categories(mounthNames)
            .xAxisTickInterval(1)
            .yAxisVisible(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("Gelir")
                    .data(totalMounthlyAverage.toArray())
            )
            )

        aaChartView?.aa_drawChartWithChartModel(chartRemainingModel)

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
            averageRemainingButton.visibility = View.GONE
            averageInComeButton.visibility = View.GONE
            averageOutCome!!.visibility = View.GONE
            flag = true
            aaChartView?.aa_refreshChartWithChartModel(chartRemainingModel)
            aaChartView?.aa_drawChartWithChartModel(chartRemainingModel)
        }

        averageOutCome?.setOnClickListener {
            averageRemainingButton!!.visibility = View.GONE
            averageInComeButton.visibility = View.GONE
            averageOutCome.visibility = View.GONE
            flag = true
            //aaChartView?.aa_drawChartWithChartModel(chartOutComeModel)
        }

        averageInCome?.setOnClickListener {
            averageRemainingButton!!.visibility = View.GONE
            averageInComeButton.visibility = View.GONE
            averageOutCome!!.visibility = View.GONE
            flag = true
           // aaChartView?.aa_drawChartWithChartModel(chartIncomeModel)
        }

    }

}