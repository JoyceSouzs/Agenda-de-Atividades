package com.example.agendadeatividades.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.agendadeatividades.entity.Activity

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(activity: Activity)

    @Delete
    fun delete(activity: Activity)

    @Update
    fun update(activity: Activity)

    @Query("SELECT * FROM activityTable")
    fun getActivities(): LiveData<List<Activity>>

    @Query("SELECT * FROM activityTable WHERE idTitle = :idTitle")
    fun getActivityId(idTitle: Int): Activity
}