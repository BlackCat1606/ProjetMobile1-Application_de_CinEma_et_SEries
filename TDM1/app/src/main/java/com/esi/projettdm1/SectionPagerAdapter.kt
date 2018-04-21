package com.esi.projettdm1

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by BlackCat on 4/18/2018.
 */
public class SectionPagerAdapter(fm:FragmentManager) : FragmentStatePagerAdapter(fm) {
    internal var  mFragmentList:MutableList<Fragment> = ArrayList<Fragment>()
    override fun getItem(position: Int): Fragment {
       return mFragmentList.get(position)
    }

    override fun getCount(): Int {
       return mFragmentList.size
    }
    public fun addFragment(fragment: Fragment)
    {
        mFragmentList.add(fragment)
    }
}