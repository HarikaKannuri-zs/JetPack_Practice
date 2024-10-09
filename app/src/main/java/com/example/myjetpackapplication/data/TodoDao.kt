package com.example.myjetpackapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myjetpackapplication.TodoData

@Dao

interface TodoDao {

    @Query("SELECT * FROM TodoData")
    fun getAllTask() : LiveData<List<TodoData>>

    @Insert
    fun addTask(todoData: TodoData)
}
