package com.example.viikko1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.viikko1.domain.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()
    private var allTasks = listOf<Task>()

    init {
        _tasks.value = listOf(
            Task(
                id = 1,
                title = "Task 1",
                description = "Description 1",
                priority = 1,
                dueDate = LocalDate.now().plusDays(1),
                done = false
            ),
            Task(
                id = 2,
                title = "Task 2",
                description = "Description 2",
                priority = 3,
                dueDate = LocalDate.now().plusDays(5),
                done = true
            ),
            Task(
                id = 3,
                title = "Task 3",
                description = "Description 3",
                priority = 2,
                dueDate = LocalDate.now().plusDays(2),
                done = false
            ),
            Task(
                id = 4,
                title = "Task 4",
                description = "Description 4",
                priority = 1,
                dueDate = LocalDate.now().plusDays(6),
                done = false
            ),
            Task(
                id = 5,
                title = "Task 5",
                description = "Description 5",
                priority = 3,
                dueDate = LocalDate.now().plusDays(8),
                done = true
            ),
            Task(
                id = 6,
                title = "Task 6",
                description = "Description 6",
                priority = 2,
                dueDate = LocalDate.now().plusDays(10),
                done = false
            ),
            Task(
                id = 7,
                title = "Task 7",
                description = "Description 7",
                priority = 1,
                dueDate = LocalDate.now().plusDays(12),
                done = false
            ),
            Task(
                id = 8,
                title = "Task 8",
                description = "Description 8",
                priority = 2,
                dueDate = LocalDate.now().plusDays(16),
                done = false
            )
        )

    }

    fun addTask(task: Task){

        _tasks.value += task
    }

    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }

    }

    fun filterByDone(done:Boolean){
        _tasks.value = _tasks.value.filter { it.done == done }
    }

    fun removeTask(id:Int){
        _tasks.value = _tasks.value.filter { it.id != id}

    }

    fun sortByDueDate(){
        _tasks.value = _tasks.value.sortedBy {it.dueDate}
    }
}