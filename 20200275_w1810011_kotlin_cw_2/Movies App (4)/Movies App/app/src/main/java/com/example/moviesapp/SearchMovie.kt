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
import android.view.View
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

class SearchMovie : AppCompatActivity() {
    private var editTextTextPersonName7: EditText? = null
    private var text: TextView? = null
    private var movie: Movie? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        editTextTextPersonName7 = findViewById(R.id.editTextTextPersonName7)
        text = findViewById(R.id.text)
    }

    fun lookup(view: View?) {
        GetMoviesByActor().execute()
    }

    fun save(view: View?) {
        if (movie != null) {
            val databaseAccess =
                Room.databaseBuilder(applicationContext, DatabaseAccess::class.java, "moviesdb")
                    .allowMainThreadQueries().build()
            databaseAccess.daoClass().addMovies(
                movie
            )
            Toast.makeText(this, "Data Saved using ROOM", Toast.LENGTH_SHORT).show()
        }
    }

    internal inner class GetMoviesByActor : AsyncTask<Void?, Void?, Void?>() {


        override fun doInBackground(vararg p0: Void?): Void? {
            val httpHandler = HttpHandler()
            // Making a request to url and getting response
            val jsonStr =
                httpHandler.makeServiceCall("https://www.omdbapi.com/?t=" + editTextTextPersonName7!!.text.toString() + "&apikey=7153abd9")
            if (jsonStr != null) {
                try {
                    val jsonObj = JSONObject(jsonStr)
                    movie = Movie(
                        jsonObj.getString("Title"),
                        jsonObj.getInt("Year"),
                        jsonObj.getString("Rated"),
                        jsonObj.getString("Released"),
                        jsonObj.getString("Runtime"),
                        jsonObj.getString("Genre"),
                        jsonObj.getString("Director"),
                        jsonObj.getString("Writer"),
                        jsonObj.getString("Actors"),
                        jsonObj.getString("Plot")
                    )
                    runOnUiThread { text!!.text = movie.toString() }
                } catch (e: JSONException) {
                    Log.e("ok", "Json parsing error: " + e.message)
                }
            } else {
                Log.e("df", "Couldn't get json from server.")
            }
            return null
        }
    }
}