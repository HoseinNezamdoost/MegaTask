package com.nzd.megatask.database

import androidx.room.*

import com.nzd.megatask.dataClass.Tasks

@Dao
interface TaskDao {
    @Insert
    fun insert(tasks: Tasks): Long

    @Delete
    fun delete(tasks: Tasks): Int

    @Query("DELETE FROM tasks")
    fun deleteAll(): Int

    @Query("SELECT * FROM tasks")
    fun getAll(): List<Tasks>

    @Query("UPDATE tasks SET isDown =:isDone WHERE id =:id")
    fun updateToDone(id: Int , isDone:Boolean)

    @Query("UPDATE tasks SET title = :title , description = :description , date = :date , isPriory = :isPriory WHERE id = :id")
    fun update(id: Int, title: String, description: String, date: String, isPriory: Boolean): Int

//    @Update
//    fun update(tasks: Tasks): Int

}