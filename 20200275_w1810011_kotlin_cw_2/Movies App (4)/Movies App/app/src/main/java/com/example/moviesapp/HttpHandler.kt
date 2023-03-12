package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.os.Bundle
import com.example.moviesapp.R
import com.example.moviesapp.AdditionalFeature.GetMoviesByTitle
import android.os.AsyncTask
import org.json.JSONObject
import org.json.JSONArray
import org.json.JSONException
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesapp.MovieDAO
import com.example.moviesapp.DatabaseAccess
import androidx.room.Room
import android.widget.Toast
import android.content.Intent
import android.util.Log
import com.example.moviesapp.SearchMovie
import com.example.moviesapp.SearchActor
import com.example.moviesapp.AdditionalFeature
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Delete
import androidx.room.Update
import com.example.moviesapp.SearchMovie.GetMoviesByActor
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class HttpHandler {
    //Referred https://www.tutorialspoint.com/android/android_json_parser.htm when developing the following code
    fun makeServiceCall(reqUrl: String?): String? {
        var response: String? = null
        try {
            val url = URL(reqUrl)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            // read the response
            val `in`: InputStream = BufferedInputStream(conn.inputStream)
            response = convertStreamToString(`in`)
        } catch (e: Exception) {
            Log.e("Hello", "Exception: " + e.message)
        }
        return response
    }

    private fun convertStreamToString(`is`: InputStream): String {
        val reader = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                sb.append(line).append('\n')
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return sb.toString()
    } //Tutorials Point -  JSON Parser [Source code]. https://www.tutorialspoint.com/android/android_json_parser.htm
}