package com.example.shotlogger.di

import android.content.Context
import androidx.room.Room
import com.example.shotlogger.data.ExerciseDao
import com.example.shotlogger.data.ExerciseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Provides
    @Singleton
    fun provideExerciseDatabase(@ApplicationContext context: Context): ExerciseDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            ExerciseDatabase::class.java,
        ).build()
    }

    @Provides
    fun provideExerciseDao(appDatabase: ExerciseDatabase): ExerciseDao {
        return appDatabase.exerciseDao
    }

}