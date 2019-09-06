package com.example.tinyandroidapp

import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

class AppleRssTask(url: String, spinner: ProgressBar?) : AsyncTask<String, Void, JSONObject>() {
    val mUrl = url
    val mSpinner = spinner

    override fun onPreExecute() {
        mSpinner?.setVisibility(View.VISIBLE)
        super.onPreExecute()
    }

    override fun doInBackground(vararg p0: String?): JSONObject? {
        try {
            var client = OkHttpClient()
            var request = Request.Builder().url(this.mUrl).build()

            var response : Response? = null

            try {
                response = client.newCall(request).execute()
            } catch (e : IOException) {

                e.printStackTrace()
            }
            var jsonData : String? = response?.body?.string()

            return JSONObject(jsonData)
        }catch(e: Exception){
            throw e
        }
    }

    override fun onPostExecute(result: JSONObject?) {
        mSpinner?.setVisibility(View.INVISIBLE)
        super.onPostExecute(result)
    }

}