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
        var x = db.getMounthlyAverages()

        val chartIncomeModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Waterfall)
            .title("Aylara Göre Ortalama Geliriniz")
            .subtitle("")
            .backgroundColor("#FFFFFF")
            .dataLabelsEnabled(true)
            .categories(arrayOf("Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"))
            .xAxisTickInterval(1)
            .yAxisVisible(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("Gelir")
                    .data(arrayOf(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6))
            )
            )
//arrayOf(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6)
        aaChartView?.aa_drawChartWithChartModel(chartIncomeModel)

        val chartOutComeModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Waterfall)
            .title("Aylara Göre Ortalama Gideriniz")
            .subtitle("")
            .backgroundColor("#FFFFFF")
            .dataLabelsEnabled(true)
            .categories(arrayOf("Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"))
            .xAxisTickInterval(1)
            .yAxisVisible(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("Gider")
                    .data(arrayOf(10.0, 11, 13, 35, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6))
            )
            )

        val chartRemainingModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Waterfall)
            .title("Aylara Göre Ortalama Kalan")
            .subtitle("")
            .backgroundColor("#FFFFFF")
            .dataLabelsEnabled(true)
            .categories(arrayOf("Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"))
            .xAxisTickInterval(1)
            .yAxisVisible(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("Kalan")
                    .data(arrayOf(35.0, 11, 13, 35, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6))
            )
            )

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
            aaChartView?.aa_drawChartWithChartModel(chartRemainingModel)
        }

        averageOutCome?.setOnClickListener {
            averageRemainingButton!!.visibility = View.GONE
            averageInComeButton.visibility = View.GONE
            averageOutCome.visibility = View.GONE
            flag = true
            aaChartView?.aa_drawChartWithChartModel(chartOutComeModel)
        }

        averageInCome?.setOnClickListener {
            averageRemainingButton!!.visibility = View.GONE
            averageInComeButton.visibility = View.GONE
            averageOutCome!!.visibility = View.GONE
            flag = true
            aaChartView?.aa_drawChartWithChartModel(chartIncomeModel)
        }

    }

}