package com.esi.projettdm1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import com.esi.projettdm1.fragments.Tab1Fragment
import com.esi.projettdm1.fragments.Tab2Fragment
import com.esi.projettdm1.fragments.Tab3Fragment
import com.esi.projettdm1.utils.BottomNavigationViewHelper


class AcceuilActivity : AppCompatActivity() {

    lateinit var mSectionPagerAdapter:SectionPagerAdapter
    lateinit var mViewPager:ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acceuil)
        supportActionBar!!.hide()
  /*    mSectionPagerAdapter = SectionPagerAdapter(supportFragmentManager)
        mViewPager = findViewById<ViewPager>(R.id.container)
        setupViewPager(mViewPager)

       var tabLayout:TabLayout = findViewById<TabLayout>(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(mViewPager)
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_assignment)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_autorenew)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_attach_file)
*/
        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavViewBar) as BottomNavigationView
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
        var menu:Menu = bottomNavigationView.menu
        var menuItem :MenuItem = menu.getItem(1)
        menuItem.setChecked(true)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_house -> {
                    val intent1 = Intent(this@AcceuilActivity, HomeActivity::class.java)
                    startActivity(intent1)
                }

                R.id.ic_search -> {

                }
                R.id.ic_circle->{
                    val intent2 = Intent(this@AcceuilActivity, CinemaActivity::class.java)
                    startActivity(intent2)
                }


            }


            false
        }

    }

      private fun setupViewPager(viewPager: ViewPager?) {

        var  adapter : SectionPagerAdapter = SectionPagerAdapter(supportFragmentManager)
        adapter.addFragment(Tab1Fragment())
        adapter.addFragment(Tab2Fragment())
        adapter.addFragment(Tab3Fragment())
        viewPager!!.setAdapter(adapter)

    }
}
