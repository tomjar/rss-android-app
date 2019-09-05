package com.example.tinyandroidapp

import android.app.Activity
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.net.URI

class RssDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rss_details)

        val albumUrl = intent.getStringExtra("albumurl")
        val albumName = intent.getStringExtra("albumname")
        val artistname =  intent.getStringExtra("artistname")

        val uri = Uri.parse(albumUrl)
        val imageViewAlbumArt = findViewById<ImageView>(R.id.imageview_album_art)

        val rssAlbumArtTask = RssAlbumArtTask(albumUrl, imageViewAlbumArt).execute().get()

        imageViewAlbumArt?.setImageBitmap(rssAlbumArtTask)
        findViewById<TextView>(R.id.textview_album_name)?.text = albumName
        findViewById<TextView>(R.id.textview_artist_name)?.text = artistname
        findViewById<TextView>(R.id.textview_misc_details)?.text = "Hello world"
    }
}
