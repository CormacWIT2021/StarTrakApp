package org.wit.startrak.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.wit.startrak.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.startrak.models.Filminglocation


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    var filmingLocation = Filminglocation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
       filmingLocation = intent.extras?.getParcelable<Filminglocation>("Filming Location")!!
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val filmingLoc = LatLng(filmingLocation.lat, filmingLocation.lng)
        val options = MarkerOptions()
            .title("Filming Location")
            .snippet("GPS : " + filmingLoc.toString())
            .draggable(true)
            .position(filmingLoc)
        map.addMarker(options)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(filmingLoc, filmingLocation.zoom))
        map.setOnMarkerDragListener(this)
        map.setOnMarkerClickListener(this)
    }

    override fun onMarkerDragStart(marker: Marker) {
    }

    override fun onMarkerDrag(marker: Marker) {
    }

    override fun onMarkerDragEnd(marker: Marker) {
        filmingLocation.lat = marker.position.latitude
        filmingLocation.lng = marker.position.longitude
        filmingLocation.zoom = map.cameraPosition.zoom
    }

    override fun onBackPressed() {
        val resultIntent = Intent()
        resultIntent.putExtra("location", filmingLocation)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
        super.onBackPressed()
    }

    override fun onMarkerClick(marker: Marker): Boolean
    {
        val filmingLoc = LatLng(filmingLocation.lat, filmingLocation.lng)
        marker.setSnippet("GPS : " + filmingLoc.toString())
        return false

    }


}