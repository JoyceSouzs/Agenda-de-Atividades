package com.example.agendadeatividades.repository

import androidx.lifecycle.LiveData
import com.example.agendadeatividades.entity.Activity

interface IActivityRepository {
    fun insertActivity(actvity: Activity)
    fun updateActivity(actvity: Activity)
    fun deleteActivity(actvity: Activity)
    fun getActivities(): LiveData<List<Activity>>
    fun getActivityId(idTitle: Int): Activity
}