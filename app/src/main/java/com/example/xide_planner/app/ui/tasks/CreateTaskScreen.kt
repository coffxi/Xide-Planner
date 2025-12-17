package com.example.xide_planner.app.ui.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xide_planner.app.R
import com.example.xide_planner.app.ui.components.Button1
import com.example.xide_planner.app.ui.components.CenteredInputField
import com.example.xide_planner.app.ui.components.OptionRow
import com.example.xide_planner.app.viewmodel.TasksViewModel
import com.example.xide_planner.app.data.model.Task
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen( onBack: () -> Unit) {
    val circleIcons = listOf(
        R.drawable.rosa,
        R.drawable.morado,
        R.drawable.verde,
        R.drawable.naranja,
        R.drawable.azul,
        R.drawable.amarillo
    )

    var taskName by remember { mutableStateOf("") }
    var isTimeEnabled by remember { mutableStateOf(false) }
    var selectedColorIndex by remember { mutableStateOf(0) }

    var repeatOption by remember { mutableStateOf("No repetir") }
    var selectedTime by remember { mutableStateOf("En cualquier momento") }
    var reminderOption by remember { mutableStateOf("Sin aviso") }
    var tagsOption by remember { mutableStateOf("Sin etiqueta") }

    var showDatePicker by remember { mutableStateOf(false) }
    var showRepeatDialog by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var showReminderDialog by remember { mutableStateOf(false) }
    var showTagDialog by remember { mutableStateOf(false) }
    var showAddTagDialog by remember { mutableStateOf(false) }

    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }
    var selectedHour by remember {
        mutableStateOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) }
    var selectedMinute by remember {
        mutableStateOf(Calendar.getInstance().get(Calendar.MINUTE)) }

    var tagsList by remember {
        mutableStateOf(
            mutableListOf(
                "Sin etiqueta",
                "Escuela",
                "Trabajo",
                "Personal"
            )
        )
    }
    var newTagName by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDateMillis ?: todayAtMidnight())
    val tasksViewModel: TasksViewModel = viewModel()

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Cerrar",
                            tint = Color(0xFF8B1D3D)
                        )
                    }
                },
                actions = {
                    Button1(
                        text = "Guardar",
                        onClick = {
                            val task = Task(
                                id = "",
                                name = taskName,
                                colorIndex = selectedColorIndex,
                                dateMillis = selectedDateMillis,
                                repeat = repeatOption,
                                time = selectedTime,
                                reminder = reminderOption,
                                tag = tagsOption
                            )

                            tasksViewModel.saveTask(task)
                            onBack()
                        },
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

            CenteredInputField(
                value = taskName,
                placeholder = "Nombre Tarea",
                onValueChange = { taskName = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(6) { index ->
                    Surface(
                        modifier = Modifier.size(42.dp),
                        shape = CircleShape,
                        color = Color(0xFFFFF3FB),
                        onClick = { selectedColorIndex = index }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(circleIcons[index]),
                                contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )

                            if (selectedColorIndex == index) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "Seleccionado",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(80.dp))

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
                        Icons.Filled.DateRange,
                        "Fecha",
                        formatDate(selectedDateMillis)
                    ) {
                        showDatePicker = true
                    }

                    OptionRow(
                        Icons.Filled.Repeat,
                        "Repetir",
                        repeatOption
                    ) {
                        showRepeatDialog = true
                    }

                    OptionRow(
                        Icons.Filled.Schedule,
                        "Tiempo",
                        selectedTime
                    ) {
                        showTimePicker = true
                    }

                    OptionRow(
                        Icons.Filled.Notifications,
                        "Recordatorio",
                        reminderOption
                    ) { showReminderDialog = true }

                    OptionRow(
                        Icons.Filled.Description,
                        "Etiqueta",
                        tagsOption
                    ) { showTagDialog = true }
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
            Image(
                painter = painterResource(R.drawable.tareas1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }

        /* ───────── DATE PICKER ───────── */
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            selectedDateMillis =
                                datePickerState.selectedDateMillis?.plus(
                                    12 * 60 * 60 * 1000
                                )
                            showDatePicker = false
                        }
                    ) {
                        Text("Aceptar", color = Color(0xFFA44683))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Cancelar", color = Color(0xFFA44683))
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = true,
                    colors = DatePickerDefaults.colors(
                        selectedDayContainerColor = Color(0xFFA44683),
                        selectedDayContentColor = Color(0xFFFFF3FB),
                        todayDateBorderColor = Color(0xFFA44683),
                        todayContentColor = Color(0xFFA44683),
                        navigationContentColor = Color(0xFFA44683),
                        weekdayContentColor = Color(0xFFA44683)
                    )
                )
            }
        }

        /* ───────── REPEAT DIALOG ───────── */
        if (showRepeatDialog) {
            Dialog(
                onDismissRequest = { showRepeatDialog = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        // ───── HEADER ─────
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Repetir tarea",
                                style = MaterialTheme.typography.titleLarge
                            )
                            IconButton(onClick = { showRepeatDialog = false }) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Cerrar",
                                    tint = Color(0xFFA44683)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        // ───── OPCIONES ─────
                        listOf(
                            "No repetir",
                            "Diariamente",
                            "Semanalmente",
                            "Mensualmente",
                            "Anualmente"
                        ).forEach { option ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { repeatOption = option }
                                    .padding(vertical = 14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = option,
                                    color = Color(0xFFA44683),
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.weight(1f)
                                )
                                RadioButton(
                                    selected = repeatOption == option,
                                    onClick = { repeatOption = option },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color(0xFFA44683)
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        /* ───────── TIME PICKER ───────── */
        if (showTimePicker) {
            Dialog(
                onDismissRequest = { showTimePicker = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // ───── Header con X ─────
                        Box(modifier = Modifier.fillMaxWidth()) {
                            IconButton(
                                onClick = { showTimePicker = false },
                                modifier = Modifier.align(Alignment.CenterEnd)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Cerrar",
                                    tint = Color(0xFFA44683)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        // ───── TÍTULO ─────
                        Text(
                            text = if (isTimeEnabled)
                                "Hacerlo ${timePrefix(selectedHour)} ${
                                    formatTime(
                                        selectedHour, selectedMinute
                                    )
                                }"
                            else
                                "Hacerlo en cualquier momento del día",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // ───── TEXTO + SWITCH ─────
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Ícono + textos
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Schedule,
                                    contentDescription = "Hora",
                                    tint = Color(0xFFA44683),
                                    modifier = Modifier.size(20.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Column {
                                    Text(
                                        text = "Hora especificada",
                                        color = Color(0xFFA44683),
                                        style = MaterialTheme.typography.bodyMedium
                                    )

                                    Text(
                                        text = "Fije una hora concreta para hacerlo",
                                        color = Color.Gray,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                // Switch a la derecha
                                Switch(
                                    checked = isTimeEnabled,
                                    onCheckedChange = {
                                        isTimeEnabled = it
                                        if (!it) {
                                            selectedTime = "En cualquier momento"
                                        }
                                    },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = Color(0xFFA44683)
                                    )
                                )
                            }
                        }

                        // ───── RELOJ ─────
                        if (isTimeEnabled) {
                            Spacer(modifier = Modifier.height(24.dp))
                            Text(
                                text = "Seleccionar hora",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFFA44683)
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                            val timePickerState = rememberTimePickerState(
                                initialHour = selectedHour,
                                initialMinute = selectedMinute,
                                is24Hour = false
                            )

                            TimePicker(
                                state = timePickerState,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(380.dp),
                                colors = TimePickerDefaults.colors(
                                    selectorColor = Color(0xFFA44683),
                                    clockDialSelectedContentColor = Color.White,
                                    clockDialUnselectedContentColor = Color(0xFFA44683),
                                    periodSelectorSelectedContainerColor = Color(0xFFA44683),
                                    periodSelectorSelectedContentColor = Color.White,
                                    periodSelectorUnselectedContainerColor = Color(0xFFFFF3FB),
                                    periodSelectorUnselectedContentColor = Color(0xFFA44683)
                                )
                            )

                            Spacer(modifier = Modifier.height(20.dp))
                            Button1(
                                text = "Aceptar",
                                onClick = {
                                    selectedHour = timePickerState.hour
                                    selectedMinute = timePickerState.minute
                                    selectedTime = formatTime(
                                        timePickerState.hour,
                                        timePickerState.minute
                                    )
                                    showTimePicker = false
                                }
                            )
                        }
                    }
                }
            }
        }

        /* ───────── REMINDER DIALOG ───────── */
        if (showReminderDialog) {
            Dialog(
                onDismissRequest = { showReminderDialog = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth(1f)   // ancho
                        .wrapContentHeight()   // alto automático
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {
                        // ───── Header con X ─────
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Recordatorio",
                                style = MaterialTheme.typography.titleLarge
                            )
                            IconButton(onClick = { showReminderDialog = false }) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Cerrar",
                                    tint = Color(0xFFA44683)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        val reminderOptions = listOf(
                            "Sin aviso" to "",
                            "Recordatorio de la mañana" to "09:00 am",
                            "Recordatorio del medio día" to "12:00 pm",
                            "Recordatorio de la tarde" to "04:00 pm",
                            "Recordatorio de la noche" to "08:00 pm"
                        )
                        reminderOptions.forEach { (option, time) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { reminderOption = option }
                                    .padding(vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // ─── Texto principal (izquierda)
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = option,
                                        color = Color(0xFFA44683), // vino
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                                // ─── Hora (derecha)
                                if (time.isNotEmpty()) {
                                    Text(
                                        text = "($time)",
                                        color = Color.Gray,
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(end = 8.dp),
                                        textAlign = TextAlign.End
                                    )
                                }
                                // ─── RadioButton
                                RadioButton(
                                    selected = reminderOption == option,
                                    onClick = { reminderOption = option },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color(0xFFA44683)
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
        /* ───────── TAG DIALOG ───────── */
        if (showTagDialog) {
            Dialog(
                onDismissRequest = { showTagDialog = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        // ───── HEADER ─────
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Etiqueta",
                                style = MaterialTheme.typography.titleLarge
                            )

                            IconButton(onClick = { showTagDialog = false }) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Cerrar",
                                    tint = Color(0xFFA44683)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // ───── CONTENEDOR SCROLLEABLE ─────
                        val listState = rememberLazyListState()

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 320.dp) // 👈 el dialog crece hasta aquí
                        ) {

                            LazyColumn(
                                state = listState,
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                items(tagsList) { option ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { tagsOption = option }
                                            .padding(vertical = 14.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = option,
                                            color = Color(0xFFA44683),
                                            modifier = Modifier.weight(1f)
                                        )

                                        RadioButton(
                                            selected = tagsOption == option,
                                            onClick = { tagsOption = option },
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = Color(0xFFA44683)
                                            )
                                        )
                                    }
                                }

                                // ───── BOTÓN AGREGAR ─────
                                item {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Button1(
                                            text = "Agregar Nueva Etiqueta",
                                            onClick = { showAddTagDialog = true },
                                            modifier = Modifier.width(270.dp)
                                        )
                                    }
                                }
                            }

                            // ───── INDICADOR DE MÁS CONTENIDO ─────
                            if (listState.canScrollForward) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = Color.LightGray,
                                    modifier = Modifier
                                        .align(Alignment.BottomCenter)
                                        .padding(bottom = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        /* ───────── ADD TAG DIALOG ───────── */
        if (showAddTagDialog) {
            Dialog(
                onDismissRequest = { showAddTagDialog = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        // ───── HEADER ─────
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Nueva Etiqueta",
                                style = MaterialTheme.typography.bodyLarge)

                            IconButton(onClick = { showAddTagDialog = false }) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Cerrar",
                                    tint = Color(0xFFA44683)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        CenteredInputField(
                            value = newTagName,
                            placeholder = "Nombre de la etiqueta",
                            onValueChange = { newTagName = it },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button1(
                                text = "Guardar",
                                onClick = {
                                    if (newTagName.isNotBlank()) {
                                        tagsList = (tagsList + newTagName).toMutableList()
                                        tagsOption = newTagName
                                        newTagName = ""
                                        showAddTagDialog = false
                                    }
                                }, modifier = Modifier.width(160.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
        /* ───────── HELPERS ───────── */

fun formatDate(millis: Long?): String {
    if (millis == null) return "Hoy"

    val formatter = SimpleDateFormat("dd MMM yyyy", Locale("es", "MX"))
    val selectedDate = Calendar.getInstance().apply {
        timeInMillis = millis
    }
    val today = Calendar.getInstance()

    return if (
        selectedDate.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
        selectedDate.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)
    ) {
        "Hoy"
    } else {
        formatter.format(Date(millis))
    }
}

fun todayAtMidnight(): Long {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.timeInMillis
}

fun formatTime(hour: Int, minute: Int): String {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
    }
    val formatter = SimpleDateFormat("hh:mm a", Locale("es", "MX"))
    return formatter.format(calendar.time)
}

fun timePrefix(hour: Int): String {
    return if (hour % 12 == 1) "la" else "las"
}
