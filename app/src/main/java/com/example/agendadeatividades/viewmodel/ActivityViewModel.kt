package com.example.agendadeatividades.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.agendadeatividades.entity.Activity
import com.example.agendadeatividades.repository.ActivityRepository

class ActivityViewModel(private val activityRepository: ActivityRepository) : ViewModel() {
    private val activityData: LiveData<List<Activity>> = activityRepository.getActivities()

    fun deleteActivity(activity: Activity) {
        activityRepository.deleteActivity(activity)
    }

    fun getActivities(): LiveData<List<Activity>> {
        return activityData
    }

    fun getActivityId(idTitle: Int): Activity {
        return activityRepository.getActivityId(idTitle)
    }

    fun salvarActivity(activity: Activity) {
        var activityGet = getActivityId(activity.idTitle)
        if (activityGet == null) {
            activityRepository.insertActivity(activity)
        } else if (activityGet.idTitle != activity.idTitle) {
            activityRepository.insertActivity(activity)
        } else {
            activityRepository.updateActivity(activity)
        }

    }
}