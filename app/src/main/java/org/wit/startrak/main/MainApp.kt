package org.wit.startrak.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.startrak.models.StarTrakMemStore
import org.wit.startrak.models.StartrakModel

class MainApp : Application(), AnkoLogger {

   // val starTrakEpisodes = ArrayList<StartrakModel>()
    val starTrakEpisodes = StarTrakMemStore()

    override fun onCreate() {
        super.onCreate()
        info("StarTrak app started")
    }
}