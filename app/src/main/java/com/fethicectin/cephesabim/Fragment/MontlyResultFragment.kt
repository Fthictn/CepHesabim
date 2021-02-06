package com.fethicectin.cephesabim.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fethicectin.cephesabim.R
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MontlyResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_montly_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val averageRemainingButton = view?.findViewById<FloatingActionButton>(R.id.averageRemaingButton)
        val averageOutCome = view?.findViewById<FloatingActionButton>(R.id.averageOutComeButton)
        val averageInCome = view?.findViewById<FloatingActionButton>(R.id.averageInComeButton)
        val containerFabButton = view?.findViewById<FloatingActionButton>(R.id.containerFabButton)
        val aaChartView = view?.findViewById<AAChartView>(R.id.aa_chart_view)
        var flag = true

        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Waterfall)
            .title("Aylara Göre Ortalama Geliriniz")
            .subtitle("subtitle")
            .backgroundColor("#FFFFFF")
            .dataLabelsEnabled(true)
            .categories(arrayOf("Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"))
            .xAxisTickInterval(1)
            .yAxisVisible(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("Tokyo")
                    .data(arrayOf(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6))
            )
            )
        aaChartView?.aa_drawChartWithChartModel(aaChartModel)

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



    }

}