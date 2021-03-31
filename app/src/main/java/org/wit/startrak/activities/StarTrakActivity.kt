package org.wit.startrak.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startrak)
        app = application as MainApp

        if(intent.hasExtra("episode_edit"))
        {
            edit = true
            startrakEpisode = intent.extras?.getParcelable<StartrakModel>("episode_edit")!!
            episodeTitle.setText(startrakEpisode.title)
            episodeSeries.setText(startrakEpisode.series)
            episodeSeason.setText(startrakEpisode.season)
            btnAdd.setText(R.string.save_episode)
        }

        btnAdd.setOnClickListener() {
            startrakEpisode.title = episodeTitle.text.toString()
            startrakEpisode.season = episodeSeason.text.toString()
            startrakEpisode.series = episodeSeries.text.toString()
            if (startrakEpisode.title.isEmpty()) {
                toast(R.string.enter_episode_title)
            } else {
                if (edit) {
                    app.starTrakEpisodes.update(startrakEpisode.copy())
                } else {
                    app.starTrakEpisodes.create(startrakEpisode.copy())
                }
            }
            info("add Button Pressed: $episodeTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
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