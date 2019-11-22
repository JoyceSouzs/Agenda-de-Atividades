package com.example.agendadeatividades.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskTable")
class Task(idTitle: Int, title: String,
           description: String) : BaseObservable() {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idTitle")
    @get:Bindable
    var idTitle: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.idTitle)
        }

    @get:Bindable
    var title: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }
    @get:Bindable
    var description: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    // Bloco inicializador
    init {
        this.idTitle = idTitle
        this.description = description
        this.title = title
    }
}