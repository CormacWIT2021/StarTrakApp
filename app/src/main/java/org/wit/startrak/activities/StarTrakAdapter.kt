package org.wit.startrak.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_startrak.view.*
import org.wit.startrak.R
import org.wit.startrak.models.StartrakModel

interface StarTrakListener {
    fun onEpisodeClick(startrakEpisode: StartrakModel)
}

class StarTrakAdapter constructor(private var startrakEpisodes: List<StartrakModel>, private val listener: StarTrakListener) :
    RecyclerView.Adapter<StarTrakAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_startrak,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val startrakEpisode = startrakEpisodes [holder.adapterPosition]
        holder.bind(startrakEpisode, listener)
    }

    override fun getItemCount(): Int = startrakEpisodes.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(startrakEpisode: StartrakModel, listener: StarTrakListener)
        {
            itemView.episodeTitle.text = startrakEpisode.title
            itemView.episodeSeries.text = startrakEpisode.series
            itemView.setOnClickListener {listener.onEpisodeClick(startrakEpisode)}
        }
    }
}

