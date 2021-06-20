package com.example.workshopmanager.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Workshop::class], version = 1)
abstract class WorkshopRoomDB : RoomDatabase() {
    abstract fun workshopDao() : WorkshopDAO
}