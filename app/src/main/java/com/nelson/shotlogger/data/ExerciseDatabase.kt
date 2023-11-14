package com.nelson.shotlogger.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nelson.shotlogger.domain.ExerciseEntity
import com.nelson.shotlogger.domain.FieldGoalDataEntity
import com.nelson.shotlogger.presentation.util.Converters

@Database(
    entities = [
        ExerciseEntity::class,
        FieldGoalDataEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ExerciseDatabase: RoomDatabase() {

    abstract val exerciseDao: ExerciseDao
    abstract val fieldGoalDao: FieldGoalDao

    companion object {
        const val DATABASE_NAME = "exercise_db"
    }
}