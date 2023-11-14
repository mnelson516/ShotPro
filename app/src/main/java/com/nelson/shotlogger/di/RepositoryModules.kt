package com.nelson.shotlogger.di
import com.nelson.shotlogger.data.ExerciseRepositoryImpl
import com.nelson.shotlogger.domain.ExerciseRepository
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