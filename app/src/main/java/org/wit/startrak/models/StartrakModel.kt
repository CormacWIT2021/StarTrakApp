package org.wit.startrak.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StartrakModel(var title: String = "",
var season: String= "",
var series: String="",
var id: Long = 0) : Parcelable