package com.esi.projettdm1.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.esi.projettdm1.R


class Tab1Fragment : Fragment(){

    private var btnTEST: Button? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater!!.inflate(R.layout.fragment1_layout, container, false)
        btnTEST = view.findViewById(R.id.btnTEST)

        btnTEST!!.setOnClickListener { Toast.makeText(activity, "TESTING BUTTON CLICK 1", Toast.LENGTH_SHORT).show() }

        return view
    }

    companion object {
        private val TAG = "Tab1Fragment"
    }
}