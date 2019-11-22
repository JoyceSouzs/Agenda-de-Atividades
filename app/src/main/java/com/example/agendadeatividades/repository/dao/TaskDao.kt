package com.example.agendadeatividades.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.agendadeatividades.entity.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)

    @Query("SELECT * FROM taskTable")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM taskTable WHERE idTitle = :idTitle")
    fun getTaskId(idTitle: Int): Task
}