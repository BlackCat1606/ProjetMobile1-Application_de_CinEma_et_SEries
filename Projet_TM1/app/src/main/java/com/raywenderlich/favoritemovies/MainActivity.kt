/*
 * Copyright (c) 2017 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.favoritemovies

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.SearchView
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.draweritems.profile.profile
import co.zsmb.materialdrawerkt.draweritems.sectionHeader
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic
import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: MoviesPagerAdapter
    //private lateinit var recyclerTabLayout: TabLayout
    private lateinit  var imageProfile:ImageView
    private var mwebView: WebView? = null
    private lateinit var result: Drawer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        toolbar.setTitle("")
        toolbar.setSubtitle("")


        result = drawer {
            toolbar = this@MainActivity.toolbar
            hasStableIds = true
            savedInstance = savedInstanceState
            showOnFirstLaunch = true

            accountHeader {
                background = R.drawable.htwo
                savedInstance = savedInstanceState
                translucentStatusBar = true

                profile("MOHAMADI Yassine", "em_mohamadi@esi.dz") {
                    iconUrl = "https://avatars3.githubusercontent.com/u/1476232?v=3&s=460"
                    identifier = 100
                }

            }

            sectionHeader("Films et Séries") {
                divider = false
            }

            primaryItem("Mes Films") {
                iicon = GoogleMaterial.Icon.gmd_movie
                onClick(openActivity(MesFilms::class))
            }
            primaryItem("Mes Séries") {
                iicon = MaterialDesignIconic.Icon.gmi_live_tv
                //onClick(openActivity(AccountHeaderActivity::class))
            }
            primaryItem("Mes Salles") {
                iicon = GoogleMaterial.Icon.gmd_room
                //onClick(openActivity(HeaderFooterActivity::class))
            }
            primaryItem("Paramètres du compte") {
                iicon = GoogleMaterial.Icon.gmd_settings
                //onClick(openActivity(HeaderFooterActivity::class))
            }

            primaryItem("Déconnexion") {
                iicon = MaterialDesignIconic.Icon.gmi_power_off
                onClick(openActivity(LogIn::class))
            }
        }

        // Get the list of movies from the JSON file
        val movies = arrayListOf("Acceuil", "Cinéma", "Série","Map")//, //"Personne", "Commentaires", "Fan")
        viewPager = findViewById(R.id.viewPager)
        pagerAdapter = MoviesPagerAdapter(supportFragmentManager, movies,this)
        viewPager.adapter = pagerAdapter
        /*recyclerTabLayout = findViewById(R.id.recyclerTabLayout)
        //recyclerTabLayout.setBackgroundColor(11)
        recyclerTabLayout.setUpWithViewPager(viewPager)*/
        val tabLayout = findViewById<TabLayout>(R.id.recyclerTabLayout)
        tabLayout.setupWithViewPager(viewPager);

        val headerView = (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.custom_tab, null, false)

        var linearLayoutOne:LinearLayout =  headerView.findViewById<LinearLayout>(R.id.ll)
        var linearLayout2:LinearLayout  =  headerView.findViewById(R.id.ll2)
        var linearLayout3:LinearLayout  = headerView.findViewById(R.id.ll3)
        var linearLayout4:LinearLayout  = headerView.findViewById(R.id.ll4)

        tabLayout.getTabAt(0)?.setCustomView(linearLayoutOne)
        tabLayout.getTabAt(1)?.setCustomView(linearLayout2)
        tabLayout.getTabAt(2)?.setCustomView(linearLayout3)
        tabLayout.getTabAt(3)?.setCustomView(linearLayout4)

        /*tabLayout.getTabAt(0)?.setIcon(R.drawable.acceuilicon)
        tabLayout.getTabAt(0)?.setText("")
        tabLayout.getTabAt(1)?.setIcon(R.drawable.cinemaicon)
        tabLayout.getTabAt(1)?.setText("")
        tabLayout.getTabAt(2)?.setIcon(R.drawable.serieicon)
        tabLayout.getTabAt(2)?.setText("")*/

        viewPager.currentItem = 0


        this.imageProfile = findViewById<ImageView>(R.id.profile)
        imageProfile.setOnClickListener {
            Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_LONG).show()
            result.openDrawer()
        }


    }
    private fun <T : Activity> openActivity(activity: KClass<T>): (View?) -> Boolean = {
        startActivity(Intent(this@MainActivity, activity.java))
        false
    }

    override  fun onBackPressed() {
        if (result.isDrawerOpen)
            result.closeDrawer()
        else
            super.onBackPressed()
    }
}