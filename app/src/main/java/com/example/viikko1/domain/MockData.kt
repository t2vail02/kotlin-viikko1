package com.example.viikko1.domain
import java.time.LocalDate

val mockData = listOf(
    Task(
        id = 1,
        title = "Mene kauppaan",
        description = "maito, murot",
        priority = 1,
        dueDate = LocalDate.now().plusDays(5),
        done = false
    ),
    Task(
        id = 2,
        title = "Tee tehtävät",
        description = "Kotlin",
        priority = 2,
        dueDate = LocalDate.now().plusDays(2),
        done = false
    ),
    Task(
        id = 3,
        title = "Peru tilaus",
        description = "disney+",
        priority = 1,
        dueDate = LocalDate.now().plusDays(11),
        done = false
    ),
    Task(
        id = 4,
        title = "Korjaa auto",
        description = "Vaihda hehkut",
        priority = 2,
        dueDate =LocalDate.now().plusDays(4),
        done = false
    ),
    Task(
        id = 5,
        title = "Siivoa",
        description = "Imuroi ja vie roskat",
        priority = 2,
        dueDate = LocalDate.now().plusDays(1),
        done = false
    ),
    )

