package com.example.agendadeatividades.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.agendadeatividades.entity.Task
import com.example.agendadeatividades.repository.TaskRepository

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    private val taskData: LiveData<List<Task>> = taskRepository.getTasks()

    fun deleteTask(task: Task) {
        taskRepository.deleteTask(task)
    }

    fun getTasks(): LiveData<List<Task>> {
        return taskData
    }

    fun getTaskId(idTitle: Int): Task {
        return taskRepository.getTaskId(idTitle)
    }

    fun saveTask(task: Task) {
        var taskGet = task
        if (task.idTitle != 0){
            taskGet = getTaskId(task.idTitle)
            if (taskGet.idTitle != task.idTitle){
                taskRepository.insertTask(task)
            } else {
                taskRepository.updateTask(task)
            }
        } else {
            taskRepository.insertTask(task)
        }
    }
}