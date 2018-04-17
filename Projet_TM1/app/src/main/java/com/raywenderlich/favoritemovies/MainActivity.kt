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

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.nshmura.recyclertablayout.RecyclerTabLayout
import android.view.ViewGroup
import android.view.ViewParent
import android.view.Window
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater








class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: MoviesPagerAdapter
    private lateinit var recyclerTabLayout: RecyclerTabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setIcon(R.mipmap.ic_launcher)
        val inflator = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflator.inflate(R.layout.search, null)
        actionBar?.setCustomView(v)*/
        setContentView(R.layout.activity_main)

        // Get the list of movies from the JSON file
        val movies = arrayListOf("Acceuil", "Cinéma", "Série", "Personne", "Commentaires", "Fan")

        viewPager = findViewById(R.id.viewPager)

        pagerAdapter = MoviesPagerAdapter(supportFragmentManager, movies)
        viewPager.adapter = pagerAdapter
        recyclerTabLayout = findViewById(R.id.recyclerTabLayout)
        recyclerTabLayout.setBackgroundColor(11)
        recyclerTabLayout.setUpWithViewPager(viewPager)
        viewPager.currentItem = 0

    }
}
