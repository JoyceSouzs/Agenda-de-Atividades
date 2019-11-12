package com.example.agendadeatividades.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activityTable")
data class Activity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idTitle")
    var idTitle: Int = 0,

    var title: String,
    var description: String
)