package com.nzd.megatask.database

import androidx.room.*
import com.nzd.megatask.dataClass.Tasks

@Dao
interface TaskDao {
    @Insert
    fun insert(tasks: Tasks): Long

    @Delete
    fun delete(tasks: Tasks) : Int

    @Query("DELETE FROM tasks")
    fun deleteAll(): Int

    @Query("SELECT * FROM tasks")
    fun getAll(): List<Tasks>

//    @Query("SELECT * FROM tasks WHERE id =:id")
//    fun get(id: Int) : Tasks

    @Update
    fun update(tasks: Tasks): Int
}