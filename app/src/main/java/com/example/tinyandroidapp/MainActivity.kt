package com.example.tinyandroidapp

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.nio.file.Files.size



class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun loadRssFeed(view: View) {
        var mWordList = mutableListOf<String>()
        var appleRssRetriever = AppleRssRetriever("https://rss.itunes.apple.com/api/v1/us/apple-music/coming-soon/all/10/explicit.json")
        var appleRssJsonObj = appleRssRetriever.execute().get()

        var results : JSONArray = appleRssJsonObj.getJSONObject("feed").getJSONArray("results")

        Log.e("MainActivity", results?.toString())


        for (i in 0 until results.length()) {
            val artistName = results.getJSONObject(i).getString("artistName")
            mWordList.add(artistName)
        }

        var mRecyclerView : RecyclerView = findViewById(R.id.recyclerview)
        var mAdapter = WordListAdapter(this, mWordList)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}
