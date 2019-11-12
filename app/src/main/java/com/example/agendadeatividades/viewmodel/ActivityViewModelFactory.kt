package com.example.agendadeatividades.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agendadeatividades.repository.ActivityRepository

class ActivityViewModelFactory(private val activityRepository: ActivityRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ActivityViewModel(activityRepository) as T
    }
}