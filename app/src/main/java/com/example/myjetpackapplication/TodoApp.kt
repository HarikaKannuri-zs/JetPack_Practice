import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myjetpackapplication.TodoData
import com.example.myjetpackapplication.TodoViewModel

@Composable
fun TodoApp(viewModel: TodoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "todoList") {
        composable("todoList") {
            TodoList(navController = navController, viewModel = viewModel)
        }
        composable("taskInput") {
            TaskInputScreen(navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
fun TodoList(navController: NavHostController, viewModel: TodoViewModel) {
    val todolist by viewModel.todoList.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Text(
            "Task List",
            fontSize = 24.sp,
            color = Color.Red,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )

        if(!todolist.isNullOrEmpty()){
            LazyColumn(content = {
                itemsIndexed(todolist!!) { index: Int, item: TodoData ->
                    TodoItem(item = item, onDelete = { viewModel.deleteTask(item.id) })
                }
            })
        }else {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text("No tasks in TodoList")
                Text("Click on Add Task Button to add tasks.")
            }
        }
        Button(
            onClick = { navController.navigate("taskInput")} , modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Add Task")

        }
    }
}

@Composable
fun TaskInputScreen(navController: NavHostController, viewModel: TodoViewModel) {
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("Task Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = taskDescription,
            onValueChange = { taskDescription = it },
            label = { Text("Task Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.addTask(taskName, taskDescription)
                navController.popBackStack()
            }, enabled = taskName.isNotEmpty(), modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save Task")
        }
    }
}

@Composable
fun TodoItem(item: TodoData, onDelete: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
                .clip(RoundedCornerShape(14.dp))
                .background(Color.LightGray)
                .padding(12.dp)
        ) {
            Row {
                Text("Title : ", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(item.taskTitle, fontSize = 18.sp)
            }

            Row {
                Text("Description : ", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(item.taskDescription, fontSize = 18.sp)
            }
        }


        Button(onClick = { onDelete() }, modifier = Modifier.align(Alignment.CenterVertically)) {
            Text("Delete")
        }
    }
}
