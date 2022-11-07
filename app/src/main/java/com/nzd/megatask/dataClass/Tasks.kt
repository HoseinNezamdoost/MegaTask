package com.nzd.megatask.dataClass

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tasks")
data class Tasks(
    @PrimaryKey(autoGenerate = false)
    val id :Int,
    var title: String,
    var description: String,
    var date:String,
    var isPriory: Boolean = false,
    var isDown: Boolean = false
)