package com.example.myjetpackapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel(){

    val todoDao = TodoApplication.todoDatabase.getTodoDao()
    val todoList: LiveData<List<TodoData>> = todoDao.getAllTask()

    fun addTask(taskTitle : String,taskDescription: String){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTask(TodoData(taskTitle = taskTitle, taskDescription = taskDescription))
        }
    }

    fun deleteTask(id : Int){
        viewModelScope.launch (Dispatchers.IO){
            todoDao.deleteTask(id)
        }
    }

}