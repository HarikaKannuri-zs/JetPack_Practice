package com.example.myjetpackapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodoList(viewModel: TodoViewModel) {
    val todolist by viewModel.todoList.observeAsState()
    var inputTask by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()

    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            OutlinedTextField(value = inputTask,
                onValueChange = { inputTask = it },
                label = { Text("Enter Task ") })

            Spacer(modifier = Modifier.padding(4.dp))
            Button(onClick = {
                viewModel.addTask(inputTask)
                inputTask = ""
            }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Text("Add Task")
            }

        }
        todolist?.let {
            LazyColumn(content = {
                itemsIndexed(it) { index: Int, item: TodoData ->
                    TodoItem(item = item)
                }
            })
        }
    }

}

@Composable
fun TodoItem(item: TodoData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.LightGray)
            .padding(12.dp)

    ) {
        Text(item.task, fontSize = 20.sp)
    }

}