package com.esi.projettdm1

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
//import butterknife.InjectView
import android.widget.EditText
//import butterknife.ButterKnife
//import sun.security.jgss.GSSUtil.login
import android.content.Intent
import com.esi.projettdm1.R
import kotlin.reflect.KClass


class LogIn : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private val REQUEST_SIGNUP = 0

    //@InjectView(R.id.input_email)
    var _emailText: EditText? = null
    //@InjectView(R.id.input_password)
    var _passwordText: EditText? = null
    //@InjectView(R.id.btn_login)
    var _loginButton: Button? = null
    //@InjectView(R.id.link_signup)
    var _signupLink: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        //ButterKnife.inject(this);

        /*_loginButton?.setOnClickListener(object : View.OnClickListener() {

            fun onClick(v: View) {
                //login()
            }
        })*/


        // la fonction login
        _loginButton?.setEnabled(false)
        val button = findViewById<Button>(R.id.link_signup)
        button.setOnClickListener {
            openActivity(SignupActivity::class)
        }

    }

    override fun onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true)
    }

    /*  fun newAccount() {
          //_signupLink?.setOnClickListener(View.OnClickListener {
          // Start the Signup activity
          //val intent = Intent(this@LogIn, SignupActivity::class.java)
          //startActivityForResult(intent, REQUEST_SIGNUP)
          openActivity(SignupActivity::class)
          //})
      }*/

    private fun <T : Activity> openActivity(activity: KClass<T>): (View?) -> Boolean = {
        startActivity(Intent(this@LogIn, activity.java))
        false
    }


}
