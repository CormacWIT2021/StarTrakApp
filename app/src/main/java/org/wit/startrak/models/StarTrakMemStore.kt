package org.wit.startrak.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}


class StarTrakMemStore : StarTrakStore, AnkoLogger {
    val starTrakEpisodes = ArrayList<StartrakModel>()

    override fun findAll(): List<StartrakModel>
    {
        return starTrakEpisodes
    }

    override fun create(starTrakEpisode: StartrakModel) {
        starTrakEpisode.id = getId()
        starTrakEpisodes.add(starTrakEpisode)
        logAll()
    }

    override fun update(starTrakEpisode: StartrakModel) {
        var foundEpisode : StartrakModel? = starTrakEpisodes.find { p -> p.id == starTrakEpisode.id}
        if(foundEpisode != null)
        {
            foundEpisode.title = starTrakEpisode.title
            foundEpisode.series = starTrakEpisode.series
            foundEpisode.season = starTrakEpisode.season
            logAll()
        }
    }

    fun logAll()
    {
        starTrakEpisodes.forEach{ info("${it}") }
    }

}