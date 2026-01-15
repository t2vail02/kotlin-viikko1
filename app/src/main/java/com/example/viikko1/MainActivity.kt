package com.example.viikko1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viikko1.domain.Task
import com.example.viikko1.ui.theme.Viikko1Theme
import com.example.viikko1.domain.mockData
import com.example.viikko1.domain.addTask
import com.example.viikko1.domain.toggleDone
import com.example.viikko1.domain.filterByDone
import com.example.viikko1.domain.sortByDueDate
import java.time.format.DateTimeFormatter
import java.time.LocalDate


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikko1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable

fun Greeting(name: String, modifier: Modifier = Modifier) {
    /*
    Text(
        text = "Hello1 $name!",
        modifier = modifier
    )
    */
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    var name by remember { mutableStateOf(value = "") }
    var description by remember {mutableStateOf(value="")}
    var priority by remember {mutableStateOf(value="1")}
    var dueDate by remember {mutableStateOf(value="YYYY-MM-DD")}
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")


    var taskList by remember { mutableStateOf(value = mockData) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text("Task list!", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))
        taskList.forEach { task ->
            Row {
                Column {
                    Text(
                        "${task.id}. ${task.title}",
                        fontSize = 18.sp,
                        textDecoration = if (task.done) TextDecoration.LineThrough else (TextDecoration.None),
                    )
                    Text(
                        task.description,
                        fontSize = 15.sp,
                        textDecoration = if (task.done) TextDecoration.LineThrough else (TextDecoration.None)
                    )
                    Text(
                        task.dueDate.format(formatter),
                        fontSize = 12.sp,
                        textDecoration = if (task.done) TextDecoration.LineThrough else (TextDecoration.None),
                        fontStyle = FontStyle.Italic
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { taskList = toggleDone(taskList, task.id) }) {
                    Text(if (task.done) "Undo" else "Done")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Title") }
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )

        OutlinedTextField(
            value = priority,
            onValueChange = { priority = it },
            label = { Text("Priority") }
        )

        OutlinedTextField(
            value = dueDate,
            onValueChange = { dueDate = it },
            label = { Text("Due Date") }
        )


        Spacer(modifier = Modifier.height(16.dp)) // spacing before button
        Row {
            Button(
                onClick = {
                    val parsedDate = LocalDate.parse(dueDate)
                    val newTask = Task(
                        id = taskList.maxOf{it.id}+1,
                        title = name,
                        description = description,
                        priority = 1,
                        dueDate = parsedDate,
                        done = false
                    )
                    taskList = addTask(taskList, newTask)
                    name=""
                    description=""
                    priority="1"
                    dueDate="YYYY-DD-MM"
                }
            ) {
                Text("Add Task")
            }
            Spacer(modifier=Modifier.width(width= 3.dp))
            Button(
                onClick = {
                    taskList = filterByDone(taskList, false)
                }
            ) {
                Text("Filter")
            }
            Spacer(modifier=Modifier.width(width= 3.dp))
            Button(
                onClick = {
                    taskList = sortByDueDate(taskList)
                },
                modifier=Modifier.width(width=200.dp)
            ) {
                Text("Sort")
            }
        }
    }

}

