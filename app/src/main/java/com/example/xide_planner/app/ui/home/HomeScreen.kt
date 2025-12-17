package com.example.xide_planner.app.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xide_planner.app.ui.components.BottomBar
import com.example.xide_planner.app.ui.components.DashboardCard
import com.example.xide_planner.app.ui.components.HomeTabs
import com.example.xide_planner.app.ui.components.NotesPreviewList
import com.example.xide_planner.app.ui.home.TasksPreviewList
import com.example.xide_planner.app.R
import com.example.xide_planner.app.viewmodel.NotesViewModel
import com.example.xide_planner.app.viewmodel.TasksViewModel

@Composable
fun HomeScreen(
    onAddTask: () -> Unit,
    onAddNote: () -> Unit,
    onCalendarClick: () -> Unit,
    onMoodClick: () -> Unit,
    onTasksClick: () -> Unit,
    onConfigClick: () -> Unit
) {

    // ViewModels
    val notesViewModel: NotesViewModel = viewModel()
    val tasksViewModel: TasksViewModel = viewModel()

    // States
    val notes by notesViewModel.notes.collectAsState()
    val tasks by tasksViewModel.tasks.collectAsState()

    // Cargar datos iniciales
    LaunchedEffect(Unit) {
        notesViewModel.loadNotes()
        tasksViewModel.loadTasks()
    }

    var selectedTab by remember { mutableStateOf(HomeSection.TASKS) }

    Scaffold(
        bottomBar = {
            BottomBar(
                onAddTaskClick = onAddTask,
                onAddNoteClick = onAddNote
            )
        },
        containerColor = Color(0xFFF6E8F1)
    ) { padding ->

        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            HomeTabs(selected = selectedTab, onSelect = { selectedTab = it })

            Spacer(Modifier.height(24.dp))

            when (selectedTab) {

                HomeSection.TASKS -> {
                    DashboardCard(
                        title = "Reporte semanal",
                        description = "No hay eventos próximos",
                        icon = R.drawable.tareas1
                    )

                    Spacer(Modifier.height(16.dp))

                    TasksPreviewList(
                        tasks = tasks,
                        onDelete = { tasks -> tasksViewModel.deleteTask(tasks.id) },
                        onEdit = { /* abrir pantalla editar */ }
                    )
                }

                HomeSection.NOTES -> {
                    val notesCount = notes.size

                    DashboardCard(
                        title = "Notas",
                        description = if (notesCount == 0)
                            "No hay notas registradas"
                        else
                            "Tienes $notesCount notas",
                        icon = R.drawable.tareas1
                    )

                    Spacer(Modifier.height(16.dp))

                    NotesPreviewList(
                        notes = notes,
                        onDelete = { note -> notesViewModel.deleteNote(note.id) },
                        onEdit = { /* abrir pantalla editar */ }
                    )
                }

                HomeSection.EMOTIONS -> {
                    DashboardCard(
                        title = "Emociones",
                        description = "Aún no registras emociones",
                        icon = R.drawable.tareas1
                    )
                }
            }
        }
    }
}


enum class HomeSection { TASKS, NOTES, EMOTIONS }
