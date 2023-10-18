package com.example.composetest.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.presentation.util.Converters

@Database(
    entities = [ExerciseEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ExerciseDatabase: RoomDatabase() {

    abstract val exerciseDao: ExerciseDao

    companion object {
        const val DATABASE_NAME = "exercise_db"
    }
}