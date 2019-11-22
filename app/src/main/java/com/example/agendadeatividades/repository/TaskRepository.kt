package com.example.agendadeatividades.repository

import androidx.lifecycle.LiveData
import com.example.agendadeatividades.entity.Task
import com.example.agendadeatividades.repository.dao.TaskDao

class TaskRepository(private val taskDao: TaskDao) : ITaskRepository {

    override fun insertTask(task: Task) {
        taskDao.insert(task)
    }

    override fun updateTask(task: Task) {
        taskDao.update(task)
    }

    override fun deleteTask(task: Task) {
        taskDao.delete(task)
    }

    override fun getTaskId(idTitle: Int): Task {
        return taskDao.getTaskId(idTitle)
    }

    override fun getTasks(): LiveData<List<Task>> {
        return taskDao.getTasks()
    }
}