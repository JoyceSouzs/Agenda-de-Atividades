package com.example.agendadeatividades.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agendadeatividades.repository.TaskRepository

class TaskViewModelFactory(private val taskRepository: TaskRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(taskRepository) as T
    }
}