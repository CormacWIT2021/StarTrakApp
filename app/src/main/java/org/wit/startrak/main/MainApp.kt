package org.wit.startrak.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.startrak.models.StarTrakJSONStore
import org.wit.startrak.models.StarTrakMemStore
import org.wit.startrak.models.StarTrakStore

class MainApp : Application(), AnkoLogger {

   // val starTrakEpisodes = ArrayList<StartrakModel>()
   // val starTrakEpisodes = StarTrakMemStore()
    lateinit var starTrakEpisodes: StarTrakStore

    override fun onCreate() {
        super.onCreate()
      //  starTrakEpisodes = StarTrakMemStore
        starTrakEpisodes = StarTrakJSONStore(applicationContext)
        info("StarTrak app started")

    }
}