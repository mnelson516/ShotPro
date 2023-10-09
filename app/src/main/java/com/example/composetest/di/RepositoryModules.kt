package com.example.composetest.di

import com.example.composetest.data.ExerciseRepository
import com.example.composetest.data.ExerciseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExerciseRepository(
        myRepositoryImpl: ExerciseRepositoryImpl
    ): ExerciseRepository
}