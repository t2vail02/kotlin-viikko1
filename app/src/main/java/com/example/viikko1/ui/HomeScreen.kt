package com.example.viikko1.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1.domain.Task
import com.example.viikko1.viewmodel.TaskViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(viewModel: TaskViewModel = viewModel()) {
    var title by remember { mutableStateOf(value = "") }
    var description by remember { mutableStateOf(value = "") }
    var priority by remember { mutableStateOf(value = "1") }
    var dueDate by remember { mutableStateOf(value = "YYYY-MM-DD") }
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")


    val taskList by viewModel.tasks.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp)
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        Text("Task list!", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(taskList) { task ->
                Row {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            "${taskList.indexOf(task)+1}. ${task.title}",
                            fontSize = 18.sp,
                            textDecoration = if (task.done) TextDecoration.LineThrough else TextDecoration.None,
                        )
                        Text(
                            task.description,
                            fontSize = 15.sp,
                            textDecoration = if (task.done) TextDecoration.LineThrough else TextDecoration.None
                        )
                        Text(
                            task.dueDate.format(formatter),
                            fontSize = 12.sp,
                            textDecoration = if (task.done) TextDecoration.LineThrough else TextDecoration.None,
                            fontStyle = FontStyle.Italic
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(onClick = { viewModel.removeTask(task.id) }) {
                        Text("Remove")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { viewModel.toggleDone(task.id) }
                    )
                }
            }
        }


        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
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

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                val parsedDate = LocalDate.parse(dueDate)
                val newTask = Task(
                    id = taskList.maxOf { it.id } + 1,
                    title = title,
                    description = description,
                    priority = 1,
                    dueDate = parsedDate,
                    done = false
                )
                viewModel.addTask(newTask)
                title =""
                description = ""
                priority = "1"
                dueDate = "YYYY-DD-MM"
            }, modifier = Modifier.weight(1f)) { Text("Add Task") }

            Button(onClick = { viewModel.filterByDone(false) },modifier = Modifier.weight(1f)) { Text("Filter") }

            Button(
                onClick = { viewModel.sortByDueDate() },
                modifier = Modifier.weight(1f)
            ) { Text("Sort") }
        }
    }
}