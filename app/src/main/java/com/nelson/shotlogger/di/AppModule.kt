package com.nelson.shotlogger.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.nelson.shotlogger.data.ExerciseDao
import com.nelson.shotlogger.data.ExerciseDatabase
import com.nelson.shotlogger.data.FieldGoalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private const val USER_PREFERENCES = "user_preferences"
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

    @Provides
    fun provideFieldGoalDao(appDatabase: ExerciseDatabase): FieldGoalDao {
        return appDatabase.fieldGoalDao
    }

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }

}