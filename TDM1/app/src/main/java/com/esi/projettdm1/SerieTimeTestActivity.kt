package com.esi.projettdm1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.esi.projettdm1.adapters.SerieTimeSelectionAdapter
import com.esi.projettdm1.adapters.TimeSelectionAdapter
import com.esi.projettdm1.data.MovieTime
import java.util.ArrayList

class SerieTimeTestActivity : AppCompatActivity() {

    lateinit var serieTimeRV: RecyclerView
    lateinit var serieTimeList: MutableList<MovieTime>
    lateinit var timeSelectionAdapter: SerieTimeSelectionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_time_test)

        init()
        serieTimeList.add(MovieTime("7:00 am", 300, 250))
        serieTimeList.add(MovieTime("11:00 am", 300, 120))
        serieTimeList.add(MovieTime("3:00 pm", 300, 60))
        serieTimeList.add(MovieTime("6:45 pm", 300, 50))
        serieTimeList.add(MovieTime("10:00 pm", 300, 170))
        timeSelectionAdapter.notifyDataSetChanged()

    }

    fun init() {

        serieTimeRV = findViewById(R.id.serieTimeRV)
        serieTimeList = ArrayList<MovieTime>()
        timeSelectionAdapter = SerieTimeSelectionAdapter(serieTimeList, this@SerieTimeTestActivity)
        val layoutManager1 = LinearLayoutManager(this@SerieTimeTestActivity, LinearLayoutManager.HORIZONTAL, false)
        serieTimeRV.layoutManager = layoutManager1
        serieTimeRV.adapter = timeSelectionAdapter

    }
}