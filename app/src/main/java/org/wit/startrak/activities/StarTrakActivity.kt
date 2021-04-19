package org.wit.startrak.activities

import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_startrak.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.startrak.R
import org.wit.startrak.helpers.readImage
import org.wit.startrak.helpers.readImageFromPath
import org.wit.startrak.helpers.showImagePicker
import org.wit.startrak.main.MainApp
import org.wit.startrak.models.Filminglocation
import org.wit.startrak.models.StartrakModel

class StarTrakActivity : AppCompatActivity(), AnkoLogger {

    var startrakEpisode = StartrakModel()
    lateinit var app : MainApp
    var edit = false
    val IMAGE_REQUEST = 1
    val FILMINGLOCATION_REQUEST = 2
    var filminglocation = Filminglocation(34.0937458, -118.3614976, 15f)

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
            episodeImage.setImageBitmap(readImageFromPath(this, startrakEpisode.image))
            if(startrakEpisode.image != null){
                chooseImage.setText(R.string.change_episode_image)
            }


        }

        filmingLocation.setOnClickListener {
            startActivityForResult(intentFor<MapsActivity>().putExtra("Filming Location", filminglocation), FILMINGLOCATION_REQUEST)
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

        chooseImage.setOnClickListener {
           showImagePicker(this, IMAGE_REQUEST)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    startrakEpisode.image = data.getData().toString()
                    episodeImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_episode_image)
                }
            }

            FILMINGLOCATION_REQUEST -> {
                if(data != null)
                {
                    filminglocation = data.extras?.getParcelable<Filminglocation>("Filming Location")!!
                }
            }
        }
    }
}