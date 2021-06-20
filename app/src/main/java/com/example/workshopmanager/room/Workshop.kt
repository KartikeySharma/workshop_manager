package com.example.workshopmanager.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "workshop_table")
class Workshop : Serializable {
    @PrimaryKey(autoGenerate = true)
    var uid = 0
    var name: String = ""
    var date: String = ""
    var applied: Boolean = false
}