package com.example.moviesapp


import androidx.room.*


@Dao
interface MovieDAO {
    /***
     * insert data
     * @param movie
     */
    @Insert
    fun addMovie(movie: Movie?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(vararg movies: Movie?)



    @Query("select * from movie where actors LIKE :query")
    fun getMovieByActor(query: String?): Movie?

    /***
     * Delete user from users table
     * @param movie
     */
    @Delete
    fun deleteMovie(movie: Movie?)

    /***
     * Update user from users table
     * @param movie
     */
    @Update
    fun updateMovie(movie: Movie?)
}