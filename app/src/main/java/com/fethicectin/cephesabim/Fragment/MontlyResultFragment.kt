package com.fethicectin.cephesabim.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fethicectin.cephesabim.R
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
        var flag = true

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