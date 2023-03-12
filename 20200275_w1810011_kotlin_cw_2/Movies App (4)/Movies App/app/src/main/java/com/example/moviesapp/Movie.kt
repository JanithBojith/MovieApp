package com.example.moviesapp


import androidx.room.*


@Entity(tableName = "movie")
class Movie(
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "year")
    var year: Int,

    @ColumnInfo(name = "rated")
    var rated: String,

    @ColumnInfo(
        name = "released"
    ) var released: String,
    @ColumnInfo(name = "runtime") var runtime: String,
    @ColumnInfo(
        name = "genre"
    ) var genre: String,
    @ColumnInfo(name = "director") var director: String,
    @ColumnInfo(
        name = "writer"
    ) var writer: String,
    @ColumnInfo(name = "actors") var actors: String,
    @ColumnInfo(
        name = "plot"
    ) var plot: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    override fun toString(): String {
        return "Movie {" +
                ", year=" + year +
                ", rated='" + rated + '\'' +
                ", released='" + released + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                '}'
    }
}