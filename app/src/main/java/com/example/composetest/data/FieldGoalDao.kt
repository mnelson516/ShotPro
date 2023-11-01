package com.example.composetest.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.composetest.domain.FieldGoalData
import kotlinx.coroutines.flow.Flow


@Dao
interface FieldGoalDao {
    @Query("SELECT * FROM `field-goals`")
    fun getFieldGoalData(): FieldGoalData?

    @Upsert()
    suspend fun upsertFieldGoalData(data: FieldGoalData)
}