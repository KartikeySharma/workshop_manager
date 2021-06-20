package com.example.workshopmanager.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkshopDAO {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert
    fun insert(workshop: Workshop)

    @Query("SELECT * FROM workshop_table")
    fun getAll(): List<Workshop>

    @Query("DELETE FROM workshop_table")
    fun deleteAll()
}