package com.example.tasks

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tasks.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task : Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * From task_table where taskId = :taskId")
    fun get(taskId:Long) : LiveData<Task>

    @Query("SELECT * From task_table order by taskId Desc")
    fun getAll() : LiveData<List<Task>>
}