package com.nzd.megatask.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Tasks(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title: String,
    val description: String,
    val date:String,
    val isPriory: Boolean = false,
    val isDown: Boolean = false
)