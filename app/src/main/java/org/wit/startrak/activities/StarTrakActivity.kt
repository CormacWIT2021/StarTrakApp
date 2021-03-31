package org.wit.startrak.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_startrak.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.startrak.R
import org.wit.startrak.models.StartrakModel

class StarTrakActivity : AppCompatActivity(), AnkoLogger {

    var startrakEpisode = StartrakModel()
    val startrakEpisodes = ArrayList<StartrakModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startrak)

        info("StarTrak Main Activity started..")


        btnAdd.setOnClickListener()
        {
            startrakEpisode.title = episodeTitle.text.toString()
            startrakEpisode.season = episodeSeason.text.toString()
            startrakEpisode.series = episodeSeries.text.toString()
            if(startrakEpisode.title.isNotEmpty())
            {
                startrakEpisodes.add(startrakEpisode.copy())
                startrakEpisodes.forEach { info("add button Pressed: ${it.title}, ${it.season}, ${it.series}")}
            }
            else
            {
                toast ("Please Enter a title")
            }

        }
    }
}