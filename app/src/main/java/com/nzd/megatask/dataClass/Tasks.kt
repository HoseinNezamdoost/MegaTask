package com.nzd.megatask.dataClass

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tasks")
data class Tasks(
    val title: String,
    val description: String,
    val date:String,
    val isPriory: Boolean = false,
    val isDown: Boolean = false
):Parcelable