package com.example.composetest.di

import android.content.Context
import androidx.room.Room
import com.example.composetest.data.ExerciseDao
import com.example.composetest.data.ExerciseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideExerciseDatabase(@ApplicationContext context: Context): ExerciseDatabase {
        return Room.databaseBuilder(
            context,
            ExerciseDatabase::class.java,
            "exercises.db"
        ).build()
    }

    @Provides
    fun provideExerciseDao(appDatabase: ExerciseDatabase): ExerciseDao {
        return appDatabase.exerciseDao
    }

}