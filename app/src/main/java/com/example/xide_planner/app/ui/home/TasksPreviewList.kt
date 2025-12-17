package com.example.xide_planner.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.xide_planner.app.data.model.Task
import com.example.xide_planner.app.R
import androidx.compose.ui.res.painterResource

@Composable
fun TasksPreviewList(
    tasks: List<Task>,
    onDelete: (Task) -> Unit,
    onEdit: (Task) -> Unit
) {
    var taskToDelete by remember { mutableStateOf<Task?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFD6EA), shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {
        Text(
            text = "Tareas recientes",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF6A1B4D)
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (tasks.isEmpty()) {
            Text("No hay tareas registradas", color = Color.DarkGray)
        } else {
            tasks.take(5).forEach { task ->
                TaskItemPreview(
                    task = task,
                    onDelete = { taskToDelete = task },
                    onEdit = { onEdit(task) }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }

    if (taskToDelete != null) {
        AlertDialog(
            onDismissRequest = { taskToDelete = null },
            title = { Text("Eliminar tarea") },
            text = { Text("¿Estás segura de que quieres eliminar esta tarea?") },
            confirmButton = {
                Button1(
                    text = "Eliminar",
                    onClick = {
                        onDelete(taskToDelete!!)
                        taskToDelete = null
                    }
                )
            },
            dismissButton = {
                Button1(
                    text = "Cancelar",
                    onClick = { taskToDelete = null }
                )
            }
        )
    }
}

@Composable
fun Button1(text: String, onClick: () -> Unit) {
    TODO("Not yet implemented")
}

@Composable
fun TaskItemPreview(
    task: Task,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = MaterialTheme.shapes.large)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(task.name, color = Color.Black)

        Row {
            // EDITAR
            IconButton(onClick = onEdit) {
                Icon(
                    painter = painterResource(id = R.drawable.edit_icon),
                    contentDescription = "Editar",
                    tint = Color(0xFF8B1D3D)
                )
            }
            // ELIMINAR
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color(0xFF8B1D3D)
                )
            }
        }
    }
}
