package com.nelson.shotlogger.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.nelson.shotlogger.domain.FieldGoalDataEntity


@Dao
interface FieldGoalDao {
    @Query("SELECT * FROM `field-goals`")
    fun getFieldGoalData(): FieldGoalDataEntity?

    @Upsert()
    suspend fun upsertFieldGoalData(data: FieldGoalDataEntity)
}