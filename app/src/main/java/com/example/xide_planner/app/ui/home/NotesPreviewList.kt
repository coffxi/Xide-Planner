package com.example.xide_planner.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.xide_planner.app.R
import com.example.xide_planner.app.data.model.Note

@Composable
fun NotesPreviewList(
    notes: List<Note>,
    onDelete: (Note) -> Unit,
    onEdit: (Note) -> Unit
) {
    // Estado de alerta
    var noteToDelete by remember { mutableStateOf<Note?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFD6EA), shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {

        Text(
            text = "Notas recientes",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF6A1B4D)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (notes.isEmpty()) {
            Text("No hay notas registradas", color = Color.DarkGray)
        } else {
            notes.take(5).forEach { note ->
                NoteItemPreview(
                    note = note,
                    onDelete = { noteToDelete = note },
                    onEdit = { onEdit(note) }
                )

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }

    // ALERTA DE CONFIRMACIÓN
    if (noteToDelete != null) {
        AlertDialog(
            onDismissRequest = { noteToDelete = null },

            title = { Text("Eliminar nota") },
            text = { Text("¿Estás segura de que quieres eliminar esta nota?") },

            confirmButton = {
                Button1(
                    text = "Eliminar",
                    onClick = {
                        onDelete(noteToDelete!!)
                        noteToDelete = null
                    }
                )
            },

            dismissButton = {
                Button1(
                    text = "Cancelar",
                    onClick = { noteToDelete = null }
                )
            }
        )
    }
}

@Composable
fun NoteItemPreview(
    note: Note,
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

        Text(note.title, color = Color.Black)

        Row {

            // EDITAR
            IconButton(onClick = onEdit) {
                Icon(
                    painter = painterResource(id = R.drawable.edit_icon),
                    contentDescription = "Editar",
                    tint = Color(0xFF8B1D3D)
                )
            }

            // ELIMINAR (Icono material)
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
