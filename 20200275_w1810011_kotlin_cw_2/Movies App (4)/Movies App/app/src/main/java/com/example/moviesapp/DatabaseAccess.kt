package com.example.moviesapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
abstract class DatabaseAccess : RoomDatabase() {
    abstract fun daoClass(): MovieDAO
}