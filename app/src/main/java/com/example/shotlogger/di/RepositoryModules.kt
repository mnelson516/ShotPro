package com.example.shotlogger.di
import com.example.shotlogger.data.ExerciseRepositoryImpl
import com.example.shotlogger.domain.ExerciseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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