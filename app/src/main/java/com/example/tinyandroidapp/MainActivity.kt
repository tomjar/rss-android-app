package com.example.tinyandroidapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    var mWordList = mutableListOf<JSONObject>()
    var mRecyclerView : RecyclerView? = null
    var mSpinner : ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mRecyclerView = findViewById(R.id.recyclerview)
        mSpinner = findViewById<ProgressBar>(R.id.progressbar_spinner)

        fab.setOnClickListener { view ->
            var wordListSize : Int = mWordList.size
            val newJsonObj = JSONObject()
            newJsonObj.put("artworkUrl100", "https://placekitten.com/400/200")
            newJsonObj.put("name", "New artist")
            newJsonObj.put("artistName", "artist name")

            mWordList.add(newJsonObj)
            mRecyclerView?.adapter?.notifyItemInserted(wordListSize)
            mRecyclerView?.smoothScrollToPosition(wordListSize)
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
        mSpinner?.setVisibility(View.VISIBLE)
        var numberOfResults = findViewById<TextView>(R.id.text_number_count)
        // https://rss.itunes.apple.com/en-us

        val number = numberOfResults.text
        val requestUrl = resources.getString(R.string.rss_feed_url).replace("\${0}", number.toString())
        Log.i("MainActivity", requestUrl)

        var appleRssTask = AppleRssTask(requestUrl, mSpinner)

        var appleRssJsonObj = appleRssTask.execute().get()
        var results = JSONArray()

        if(appleRssJsonObj.has("feed")){
            val feed = appleRssJsonObj.getJSONObject("feed")
            if(feed.has("results")){
                results = feed.getJSONArray("results")
            }
        }

        Log.i("MainActivity", results?.toString())

        mWordList.clear()
        for (i in 0 until results.length()) {
            mWordList.add(results.getJSONObject(i))
        }

        var mAdapter = WordListAdapter(this, mWordList)
        mRecyclerView?.adapter = mAdapter
        mRecyclerView?.layoutManager = LinearLayoutManager(this)
    }
}
