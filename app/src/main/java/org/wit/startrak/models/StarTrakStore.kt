package org.wit.startrak.models

interface StarTrakStore {
    fun findAll(): List<StartrakModel>
    fun create(starTrakEpisode: StartrakModel)
    fun update(starTrakEpisode: StartrakModel)
}