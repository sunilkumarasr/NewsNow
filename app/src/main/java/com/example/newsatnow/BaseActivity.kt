package com.example.newsatnow

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import java.util.Locale
import androidx.core.content.edit
import com.example.newsatnow.view.UserIntrestsActivity
import com.google.android.gms.location.LocationServices

open class BaseActivity : AppCompatActivity() {
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        //getLocation()
    }
     @SuppressLint("MissingPermission", "SetTextI18n")
     open fun getLocation() {
         if (checkPermissions()) {
             if (isLocationEnabled()) {
                 mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                     val location: Location? = task.result
                     if (location != null) {
                         val geocoder = Geocoder(this, Locale.getDefault())
                         val list: List<Address> =
                             geocoder.getFromLocation(location.latitude, location.longitude, 1)!!
                         val locationPref = getSharedPreferences("LocationPref", Context.MODE_PRIVATE)
                         locationPref.edit {
                             putString("location", list[0].locality)
                             apply()
                         }
                         val userLocation = locationPref.getString("location", "")
                         if (userLocation != "") {
                             val intent = Intent(this, UserIntrestsActivity::class.java)
                             startActivity(intent)
                         }
                         Log.d("Location", "${list[0].locality}")
                     }
                 }
             } else {
                 Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
                 val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                 startActivity(intent)
             }
         } else {
             requestPermissions()
         }
     }
     private fun isLocationEnabled(): Boolean {
         val locationManager: LocationManager =
             getSystemService(Context.LOCATION_SERVICE) as LocationManager
         return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
             LocationManager.NETWORK_PROVIDER
         )
     }
     private fun checkPermissions(): Boolean {
         if (ActivityCompat.checkSelfPermission(
                 this,
                 Manifest.permission.ACCESS_COARSE_LOCATION
             ) == PackageManager.PERMISSION_GRANTED &&
             ActivityCompat.checkSelfPermission(
                 this,
                 Manifest.permission.ACCESS_FINE_LOCATION
             ) == PackageManager.PERMISSION_GRANTED
         ) {
             return true
         }
         return false
     }
     private fun requestPermissions() {
         ActivityCompat.requestPermissions(
             this,
             arrayOf(
                 Manifest.permission.ACCESS_COARSE_LOCATION,
                 Manifest.permission.ACCESS_FINE_LOCATION
             ),
             permissionId
         )
     }
     @SuppressLint("MissingSuperCall")
     override fun onRequestPermissionsResult(
         requestCode: Int,
         permissions: Array<String>,
         grantResults: IntArray
     ) {
         if (requestCode == permissionId) {
             if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                 Toast.makeText(this, "Please wait location details are loading", Toast.LENGTH_LONG).show()
                 getLocation()
             }
         }
     }
}
