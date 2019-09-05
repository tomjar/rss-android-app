package com.example.tinyandroidapp

import android.graphics.Bitmap
import android.os.AsyncTask
import android.widget.ImageView
import java.io.IOException
import java.lang.Exception
import android.graphics.BitmapFactory
import java.net.URL


class RssAlbumArtTask(url: String, imageView : ImageView) : AsyncTask<Void, Void, Bitmap>() {

    private var mUrl = url
    private var mImageView = imageView

    override fun doInBackground(vararg p0: Void?): Bitmap? {
        try {
            try {
                val urlConnection = URL(mUrl)
                val connection = urlConnection.openConnection()
                connection.setDoInput(true)
                connection.connect()
                val input = connection.getInputStream()
                return BitmapFactory.decodeStream(input)
            } catch (e : IOException) {

                e.printStackTrace()
            }
        }catch(e: Exception){
            throw e
        }
        return null
    }

    override fun onPostExecute(result : Bitmap?){
        super.onPostExecute(result)
        mImageView.setImageBitmap(result)
    }
}