package com.example.composetest.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ExerciseEntity::class],
    version = 1
)
abstract class ExerciseDatabase: RoomDatabase() {

    abstract val exerciseDao: ExerciseDao

    companion object {
        const val DATABASE_NAME = "exercise_db"
    }
}