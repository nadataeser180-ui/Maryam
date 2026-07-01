package com.maryam.datacollector.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {

    @Insert
    suspend fun insert(data: DataEntity)

    @Delete
    suspend fun delete(data: DataEntity)

    @Query("SELECT * FROM data_table ORDER BY id DESC")
    suspend fun getAllData(): List<DataEntity>

    @Query("DELETE FROM data_table")
    suspend fun deleteAll()
}
