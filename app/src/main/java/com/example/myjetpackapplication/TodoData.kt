package com.example.myjetpackapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoData(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var taskTitle: String,
    var taskDescription: String
)
