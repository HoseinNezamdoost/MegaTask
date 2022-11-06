package com.nzd.megatask.dataClass

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tasks")
data class Tasks(
    @PrimaryKey(autoGenerate = true)
    val id :Int = 0,
    val title: String,
    val description: String,
    val date:String,
    var isPriory: Boolean = false,
    val isDown: Boolean = false
)