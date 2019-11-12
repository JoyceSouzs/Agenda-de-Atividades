package com.example.agendadeatividades.repository

import androidx.lifecycle.LiveData
import com.example.agendadeatividades.entity.Activity
import com.example.agendadeatividades.repository.dao.ActivityDao

class ActivityRepository(val activityDao: ActivityDao) : IActivityRepository {

    override fun insertActivity(actvity: Activity) {
        activityDao.insert(actvity)
    }

    override fun updateActivity(actvity: Activity) {
        activityDao.update(actvity)
    }

    override fun deleteActivity(actvity: Activity) {
        activityDao.delete(actvity)
    }

    override fun getActivityId(idTitle: Int): Activity {
        return activityDao.getActivityId(idTitle)
    }

    override fun getActivities(): LiveData<List<Activity>> {
        return activityDao.getActivities()
    }
}