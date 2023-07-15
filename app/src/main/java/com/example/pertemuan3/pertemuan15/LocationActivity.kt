package com.example.pertemuan3.pertemuan15

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationListenerCompat
import com.example.pertemuan3.MapsActivity
import com.example.pertemuan3.R

class LocationActivity : AppCompatActivity(), LocationListenerCompat {
    lateinit var txtLokasi:TextView
    lateinit var btnLokasi:Button

    var lat = 0.0
    var lng = 0.0
    var minTime: Long = 0
    var minDistance: Float = 0f
    var locProvider: String? = null
    lateinit var locMgr: LocationManager
    lateinit var lastKnownLocation: Location

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        txtLokasi = findViewById(R.id.txtLokasi)
        btnLokasi = findViewById(R.id.btnLocation)
        requestPermission()

        locMgr = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locProvider = LocationManager.GPS_PROVIDER

        lastKnownLocation = locMgr.getLastKnownLocation(locProvider!!)!!

        lat = lastKnownLocation.latitude
        lng = lastKnownLocation.longitude

        var cr = Criteria()
        cr.accuracy = Criteria.ACCURACY_FINE
        locProvider = locMgr?.getBestProvider(cr, false)
        minTime = 10 * 1000
        minDistance = 10f

        btnLokasi.setOnClickListener {
            var i = Intent(this,MapsActivity::class.java)
            startActivity(i)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        locMgr.requestLocationUpdates(locProvider!!, minTime, minDistance,this)
    }

    fun requestPermission(){
        var strPermission1 = Manifest.permission.ACCESS_FINE_LOCATION
        var strPermission2 = Manifest.permission.ACCESS_COARSE_LOCATION
        var grant1 = ContextCompat.checkSelfPermission(this, strPermission1)
        var grant2 = ContextCompat.checkSelfPermission(this, strPermission2)
        if(grant1 != PackageManager.PERMISSION_GRANTED && grant2 != PackageManager.PERMISSION_GRANTED){
            var permissions = arrayOf(strPermission1, strPermission2)
            ActivityCompat.requestPermissions(this, permissions, 1)
        }
    }

    override fun onLocationChanged(location: Location) {
        // Setiap perubahan lokasi ada disini
        txtLokasi.text = "Lokasi Saat Ini: " + location.latitude + ", " + location.longitude
        Log.e("Latitude: ", "" + location.latitude)
        Log.e("Longitude: ", "" + location.longitude)
    }

}