package com.example.myjetpackapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel(){

    val todoDao = TodoApplication.todoDatabase.getTodoDao()
    val todoList: LiveData<List<TodoData>> = todoDao.getAllTask()

    fun addTask(task : String){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTask(TodoData(task = task))
        }
    }

}