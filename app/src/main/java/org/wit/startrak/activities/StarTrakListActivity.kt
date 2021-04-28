package org.wit.startrak.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_startrak_list.*
import org.jetbrains.anko.intentFor

import org.wit.startrak.R
import org.wit.startrak.main.MainApp

import org.jetbrains.anko.startActivityForResult
import org.wit.startrak.models.StartrakModel

class StarTrakListActivity : AppCompatActivity(), StarTrakListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startrak_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadEpisodes()

        toolbar.title = title
        setSupportActionBar(toolbar)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<StarTrakActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onEpisodeClick(startrakEpisode: StartrakModel) {
        startActivityForResult(intentFor<StarTrakActivity>().putExtra("episode_edit", startrakEpisode),0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        loadEpisodes()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadEpisodes()
    {
        showEpisodes(app.starTrakEpisodes.findAll())
    }

    fun showEpisodes(starTrakEpisodes: List<StartrakModel>)
    {
        recyclerView.adapter = StarTrakAdapter(starTrakEpisodes, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}