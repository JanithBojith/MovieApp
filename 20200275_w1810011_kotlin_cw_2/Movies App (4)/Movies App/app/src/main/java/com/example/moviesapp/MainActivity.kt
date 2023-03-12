package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import androidx.room.Room
import android.widget.Toast
import android.content.Intent
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun registerMovieOnClick(view: View?) {
        val databaseAccess =
            Room.databaseBuilder(applicationContext, DatabaseAccess::class.java, "moviesdb")
                .allowMainThreadQueries().build()
        databaseAccess.daoClass().addMovies(
            Movie(
                "The Shawshank Redemption",
                1994,
                "R",
                "14 Oct 1994",
                "142 min",
                "Drama",
                "Frank Darabont",
                "Stephen King, Frank Darabont",
                "Tim Robbins, Morgan Freeman, Bob Gunton",
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."
            ),
            Movie(
                "Batman: The Dark Knight Returns, Part 1",
                2012,
                "PG-13",
                "25 Sep 2012",
                "76 min",
                "Animation, Action, Crime, Drama, Thriller",
                "Jay Oliva",
                """
     Bob Kane (character created by: Batman), Frank Miller (comic book), Klaus Janson (comic book), Bob
     Goodman
     """.trimIndent(),
                "Peter Weller, Ariel Winter, David Selby, Wade Williams",
                """
     Batman has not been seen for ten years. A new breed
     of criminal ravages Gotham City, forcing 55-year-old Bruce Wayne back
     into the cape and cowl. But, does he still have what it takes to fight
     crime in a new era?
     """.trimIndent()
            ) //ADD MORE HERE
        )
        Toast.makeText(this, "Data Saved using ROOM", Toast.LENGTH_SHORT).show()
    }

    fun displayMoviesOnClick(view: View?) {
        val intent = Intent(this, SearchMovie::class.java)
        startActivity(intent)
    }

    fun favioritesOnClick(view: View?) {
        val intent = Intent(this, SearchActor::class.java)
        startActivity(intent)
    }

    fun editMoviesOnClick(view: View?) {
        val intent = Intent(this, AdditionalFeature::class.java)
        startActivity(intent)
    }
}