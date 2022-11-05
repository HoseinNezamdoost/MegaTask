package com.nzd.megatask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nzd.megatask.dataClass.Tasks
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Tasks::class], version = 1 , exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDao() : TaskDao

}