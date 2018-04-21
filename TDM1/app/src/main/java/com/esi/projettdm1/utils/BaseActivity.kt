package com.esi.projettdm1

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

abstract class BaseActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    protected lateinit var navigationView: BottomNavigationView

    internal abstract val contentViewId: Int

    internal abstract val navigationMenuItemId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewId)

        navigationView = findViewById<BottomNavigationView>(R.id.navigation) as BottomNavigationView
        navigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        updateNavigationBarState()
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    public override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navigationView.postDelayed({
            val itemId = item.itemId
            if (itemId == R.id.navigation_home) {
                startActivity(Intent(this, HomeActivity::class.java))
            }
            finish()
        }, 300)
        return true
    }

    private fun updateNavigationBarState() {
        val actionId = navigationMenuItemId
        selectBottomNavigationBarItem(actionId)
    }

    internal fun selectBottomNavigationBarItem(itemId: Int) {
        val item = navigationView.menu.findItem(itemId)
        item.isChecked = true
    }

}
