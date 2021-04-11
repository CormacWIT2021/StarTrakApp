package org.wit.startrak.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StartrakModel(var image: String = "", var id: Long = 0, var title: String = "",
var season: String= "",
var series: String=""
) : Parcelable