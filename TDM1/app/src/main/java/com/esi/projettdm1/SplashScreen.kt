package com.esi.projettdm1

import android.content.Intent
import android.graphics.Typeface
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import com.esi.projettdm1.utils.FontChanger

class SplashScreen : AppCompatActivity() {

    lateinit var regular: Typeface
    lateinit var bold: Typeface
    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger: FontChanger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        init()

        boldFontChanger.replaceFonts(this.findViewById<View>(android.R.id.content) as ViewGroup)

        val handler = Handler()
        handler.postDelayed({ startActivity(Intent(this@SplashScreen,   HomeActivity::class.java)) }, 2000)
    }

    fun init() {

        //Changing the font throughout the activity
        regular = Typeface.createFromAsset(assets, "fonts/product_san_regular.ttf")
        bold = Typeface.createFromAsset(assets, "fonts/product_sans_bold.ttf")
        regularFontChanger = FontChanger(regular)
        boldFontChanger = FontChanger(bold)

    }
}
