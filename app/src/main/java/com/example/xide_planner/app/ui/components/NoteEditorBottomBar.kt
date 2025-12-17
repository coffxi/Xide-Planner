package com.example.xide_planner.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NoteEditorBottomBar(
    onToggleTextCase: () -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit,
    onSave: () -> Unit
) {
    Surface(
        color = Color(0xFFFFF1FC)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onToggleTextCase,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.TextFields,
                    contentDescription = "Formato",
                    tint = Color(0xFF8B1D3D),
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(
                onClick = onUndo,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Undo,
                    contentDescription = "Deshacer",
                    tint = Color(0xFF8B1D3D),
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(
                onClick = onRedo,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Redo,
                    contentDescription = "Rehacer",
                    tint = Color(0xFF8B1D3D),
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(
                onClick = onSave,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Guardar",
                    tint = Color(0xFF8B1D3D),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
