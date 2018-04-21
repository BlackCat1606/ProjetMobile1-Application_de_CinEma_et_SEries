package com.esi.projettdm1

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.profile.profile
import co.zsmb.materialdrawerkt.draweritems.sectionHeader
import com.esi.projettdm1.utils.BottomNavigationViewHelper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic
import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_cinema.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.reflect.KClass


class Map : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var result: Drawer

    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap?) {
        var lm: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        var location: Location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
  //      var longitude: Double = location.getLongitude()
    //    var latitude: Double = location.getLatitude()
        val sydney = LatLng(55.70, 13.19)
        p0?.addMarker(MarkerOptions().position(sydney)
                .title("Commencer"))?.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        p0?.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,12.0f))
        //p0?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 12.0f))
    }

    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        if (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment != null){
            Log.d("oui", "in")

            val mapFragment = supportFragmentManager
                    .findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }else{
            Log.d("non", "no")
        }








    }

    private fun <T : Activity> openActivity(activity: KClass<T>, a:String): (View?) -> Boolean = {
        var myIntent:Intent = Intent(this@Map, activity.java)
        myIntent.putExtra("code",a)
        startActivity(myIntent)
        false
    }

    override fun onBackPressed() {
        // code here to show dialog
        //super.onBackPressed()  // optional depending on your needs
        this.finish()
        System.exit(0);
    }
}
