package com.example.xide_planner.app.ui.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.xide_planner.app.ui.components.AppBackground
import com.example.xide_planner.app.ui.components.Button1
import com.example.xide_planner.app.ui.components.CenteredInputField
import com.example.xide_planner.app.ui.components.OptionRow
import com.example.xide_planner.app.ui.theme.AppThemeType
import com.example.xide_planner.app.ui.theme.getThemeConfig
import com.example.xide_planner.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen() {
    val themeConfig = getThemeConfig(AppThemeType.PINK)

    var taskName by remember { mutableStateOf("") }

    var selectedColorIndex by remember { mutableStateOf(0) }

    var selectedDate by remember { mutableStateOf("Hoy") }
    var repeatOption by remember { mutableStateOf("Apagado") }
    var selectedTime by remember { mutableStateOf("En cualquier momento") }
    var reminderOption by remember { mutableStateOf("Sin aviso") }
    var tagsOption by remember { mutableStateOf("Sin etiqueta") }
    AppBackground(theme = themeConfig) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { },
                    navigationIcon = {
                        IconButton(onClick = { /* cerrar pantalla */ }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Cerrar"
                            )
                        }
                    },
                    actions = {
                        Button1(
                            text = "Guardar",
                            onClick = { /* guardar tarea */ },
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )

            }
        ) { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // ───────── Nombre Tarea (Input) ─────────
                CenteredInputField(
                    value = taskName,
                    placeholder = "Nombre Tarea",
                    onValueChange = { taskName = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))


                // ───────── Seleccionar color de la tarea: Botón opción ─────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(6) { index ->

                        Surface(
                            modifier = Modifier.size(42.dp),
                            shape = CircleShape,
                            color = Color.LightGray,
                            onClick = {
                                selectedColorIndex = index
                            }
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                if (selectedColorIndex == index) {
                                    Icon(
                                        imageVector = Icons.Filled.Check,
                                        contentDescription = "Seleccionado",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(80.dp))

                // ───────── Contenedor inferior ─────────
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F5F5)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OptionRow(
                            icon = Icons.Filled.DateRange,
                            title = "Fecha",
                            value = selectedDate,
                            onClick = { /* abrir modal fecha */ }
                        )

                        OptionRow(
                            icon = Icons.Filled.Repeat,
                            title = "Repetir",
                            value = repeatOption,
                            onClick = { }
                        )

                        OptionRow(
                            icon = Icons.Filled.Schedule,
                            title = "Tiempo",
                            value = selectedTime,
                            onClick = { }
                        )

                        OptionRow(
                            icon = Icons.Filled.Notifications,
                            title = "Recordatorio",
                            value = reminderOption,
                            onClick = { }
                        )

                        OptionRow(
                            icon = Icons.Filled.Description,
                            title = "Etiqueta",
                            value = tagsOption,
                            onClick = { }
                        )

                    }
                }

                // ───────── Imagen de decoración ─────────
                Spacer(modifier = Modifier.height(18.dp))

                Image(
                    painter = painterResource(id = R.drawable.tareas1),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
        }
    }
}
