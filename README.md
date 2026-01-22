# Viikko1

Tehtävälista

# Datamalli

## Task

```kotlin
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Int,
    val dueDate: LocalDate,
    val done: Boolean
)
```
1. id: ID taskille
2. title: Taskin pääpointti
3. description: Kuvaus tehtävästä
4. priority: Tärkeysjärjestys
5. dueDate: Milloin tehtävän tulisi olla suoritettuna
6. done: True/False voidaan tarkastaa onko tehtävä jo suoritettu.

## Funktiot

### `addTask(list: List<Task>, task: Task): List<Task>`
Lisää uuden tehtävän listan perään.

### `toggleDone(list: List<Task>, id: Int): List<Task>`
Voi kääntää taskin tilan true->false tai toisinpäin.

### `filterByDone(list:List<Task>,done:Boolean):List<Task>`
Suodattaa tehtävät

### `sortByDueDate(list:List<Task>):List<Task>`
Järjestää tehtävät duedaten mukaan.

# Viikko2

### Compose tilanhallinta

Jetpack composessa UI rakentuu aina tilan(state) mukaan. Kun tila muuttuu, compose piirtää uudelleen tarvittavat osat UI:sta. UI ei siis käsittele manuaalisesti näkymien tilaa, vaan se päivittyy state-muuttujien mukaan.

### ViewModel vs. remember

ViewModel pysyy muistissa, vaikka composable muuttuu (esim näytön kääntyminen portrait->landscape). Composable taas kyseisessä muutoksessa hävittää muistista tiedon. 




