package com.nzd.megatask.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nzd.megatask.dataClass.Tasks

@Dao
interface TaskDao {
    @Insert
    fun insert(tasks: Tasks): Int

    @Delete
    fun delete(tasks: Tasks): Int

    @Query("DELETE FROM tasks")
    fun deleteAll(): Int

    @Query("SELECT * FROM tasks")
    fun getAll(): Int

    @Query("UPDATE tasks SET title = :title , description = :description , date = :date , isPriory = :isPriory WHERE id = :id")
    fun update(id: Int, title: String, description: String, date: String, isPriory: Boolean): Int

    @Update
    fun update(tasks: Tasks): Int

}