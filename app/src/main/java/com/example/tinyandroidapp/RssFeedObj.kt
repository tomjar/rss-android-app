package com.example.tinyandroidapp

import org.json.JSONObject

class RssFeedObj(jsonObject: JSONObject){
    var mjsonObject = jsonObject

    private var _albumImageUrl : String = ""
    var albumImageUrl : String
        get() {
            if(mjsonObject.has("artworkUrl100")) {
                _albumImageUrl = mjsonObject.getString("artworkUrl100")
            }
            return _albumImageUrl
        }
        set(value) {
            _albumImageUrl = value
        }

    private var _albumName : String = ""
    var albumName : String
        get() {
            if(mjsonObject.has("name")) {
                _albumName = mjsonObject.getString("name")
            }

            return _albumName
        }
        set(value) {
            _albumName = value
        }

    private var _artistName : String = ""
    var artistName : String
        get() {
            if(mjsonObject.has("artistName")) {
                _artistName = mjsonObject.getString("artistName")
            }
            return _artistName
        }
        set(value) {
            _artistName = value
        }
}