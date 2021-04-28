package org.wit.startrak.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.startrak.helpers.*
import java.util.*
import kotlin.collections.ArrayList

val JSON_FILE = "starTrakEpisodes.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<StartrakModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class StarTrakJSONStore : StarTrakStore, AnkoLogger {

    val context: Context
    var starTrakEpisodes = mutableListOf<StartrakModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<StartrakModel> {
        return starTrakEpisodes
    }

    override fun create(starTrakEpisode: StartrakModel) {
        starTrakEpisode.id = generateRandomId()
        starTrakEpisodes.add(starTrakEpisode)
        serialize()
    }

    override fun update(starTrakEpisode: StartrakModel) {
        val starTrakEpisodeList = findAll() as ArrayList<StartrakModel>
        var foundEpisode: StartrakModel? = starTrakEpisodeList.find {p -> p.id == starTrakEpisode.id}

        if(foundEpisode !=null)
        {
            foundEpisode.title = starTrakEpisode.title
            foundEpisode.series = starTrakEpisode.series
            foundEpisode.season = starTrakEpisode.season
            foundEpisode.image = starTrakEpisode.image
            foundEpisode.lng = starTrakEpisode.lng
            foundEpisode.lat = starTrakEpisode.lat
            foundEpisode.zoom = starTrakEpisode.zoom
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(starTrakEpisodes, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        starTrakEpisodes = Gson().fromJson(jsonString, listType)
    }


}
