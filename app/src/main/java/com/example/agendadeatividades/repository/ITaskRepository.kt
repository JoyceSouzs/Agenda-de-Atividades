package com.example.agendadeatividades.repository

import androidx.lifecycle.LiveData
import com.example.agendadeatividades.entity.Task

interface ITaskRepository {
    fun insertTask(task: Task)
    fun updateTask(task: Task)
    fun deleteTask(task: Task)
    fun getTasks(): LiveData<List<Task>>
    fun getTaskId(idTitle: Int): Task
}