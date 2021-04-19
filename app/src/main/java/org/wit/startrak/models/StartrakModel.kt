package org.wit.startrak.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StartrakModel(var image: String = "",
                         var id: Long = 0,
                         var title: String = "",
                         var season: String= "",
                         var series: String="",
                         var lat: Double = 0.0,
                         var lng: Double = 0.0,
                         var zoom: Float = 0f
) : Parcelable

@Parcelize
data class Filminglocation(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f
) : Parcelable