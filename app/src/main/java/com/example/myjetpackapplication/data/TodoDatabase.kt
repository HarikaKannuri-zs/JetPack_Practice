package com.example.myjetpackapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myjetpackapplication.TodoData

@Database(entities = [TodoData ::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    companion object{
        const val NAME  = "Todo_DB"
    }

    abstract fun getTodoDao() : TodoDao
}