package com.fethicectin.orderly.Utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fethicectin.cephesabim.Activity.MainActivity
import com.fethicectin.cephesabim.Fragment.IncomeOutcomeFragment
import com.fethicectin.cephesabim.Fragment.MontlyResultFragment

class MainTabAdapter(private val context: Context, fragmentManager: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return IncomeOutcomeFragment()
            }
            1 -> {
                return MontlyResultFragment()
            }
            else -> return IncomeOutcomeFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}