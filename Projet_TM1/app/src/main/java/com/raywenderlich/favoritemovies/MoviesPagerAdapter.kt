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
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View

//private const val MAX_VALUE = 200

// 1
class MoviesPagerAdapter(fragmentManager: FragmentManager, private val movies: ArrayList<String>,private var a:FragmentActivity) :
        FragmentStatePagerAdapter(fragmentManager) {
    // 2
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            Log.d("0", "khra" + position)
            return AcceuilFragment.newInstance(movies[position])
        }
        if (position == 1) {
            Log.d("1", "khra" + position)
            return MovieFragment.newInstance(movies[position /*% movies.size*/])
        }
        if (position==2){
            return  SerieFragment.newInstance(movies[position])
        }else {
            Log.d("4", "khra" + position)
            return MapFragment.newInstance(movies[position],this.a)
        }
    }

    // 3
    override fun getCount(): Int {
        return movies.size //* MAX_VALUE
    }

    override fun getPageTitle(position: Int): CharSequence {
        return movies[position /*% movies.size*/]
    }
}