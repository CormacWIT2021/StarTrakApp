package org.wit.startrak.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_startrak.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.startrak.R
import org.wit.startrak.main.MainApp
import org.wit.startrak.models.StartrakModel

class StarTrakActivity : AppCompatActivity(), AnkoLogger {

    var startrakEpisode = StartrakModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startrak)
        app = application as MainApp

        btnAdd.setOnClickListener()
        {
            startrakEpisode.title = episodeTitle.text.toString()
            startrakEpisode.season = episodeSeason.text.toString()
            startrakEpisode.series = episodeSeries.text.toString()
            if (startrakEpisode.title.isNotEmpty()) {
                app.starTrakEpisodes.add(startrakEpisode.copy())
                info("Add button pressed: $episodeTitle")
                for (i in app.starTrakEpisodes.indices) {
                    info("StarTrak Episode [$i]:${app.starTrakEpisodes}")
                }
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast("Please Enter a title")
            }

        }

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_startrakepisode, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item?.itemId) {
                R.id.item_cancel -> {
                    finish()
                }
            }
            return super.onOptionsItemSelected(item)
        }
    }