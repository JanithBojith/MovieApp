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

class SearchActor : AppCompatActivity() {
    private var editTextTextPersonName7: EditText? = null
    private var text: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_actor)
        editTextTextPersonName7 = findViewById(R.id.editTextTextPersonName7)
        text = findViewById(R.id.text)
    }

    fun lookup(view: View?) {
        val databaseAccess =
            Room.databaseBuilder(applicationContext, DatabaseAccess::class.java, "moviesdb")
                .allowMainThreadQueries().build()
        val movie = databaseAccess.daoClass()
            .getMovieByActor("%" + editTextTextPersonName7!!.text.toString().toUpperCase() + "%")
        text!!.text = movie.toString()
    }
}