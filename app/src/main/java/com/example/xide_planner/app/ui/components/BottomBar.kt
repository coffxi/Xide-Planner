package com.example.xide_planner.app.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.xide_planner.app.R

@Composable
fun BottomBar(
    onAddTaskClick: () -> Unit = {},
    onAddNoteClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onCalendarClick: () -> Unit = {},
    onMoodClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val iconSize = 28.dp
    val calendarSize = 32.dp

    NavigationBar(
        containerColor = Color(0xFFF4C9D9),
        tonalElevation = 0.dp,
        modifier = Modifier.height(70.dp)
    ) {

        // 🏠 HOME
        NavigationBarItem(
            selected = false,
            onClick = onHomeClick,
            icon = {
                Icon(
                    painterResource(id = R.drawable.home_icon),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.DarkGray
            )
        )

        // 📅 CALENDARIO
        NavigationBarItem(
            selected = false,
            onClick = onCalendarClick,
            icon = {
                Icon(
                    painterResource(id = R.drawable.calendar_icon),
                    contentDescription = null,
                    modifier = Modifier.size(calendarSize)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.DarkGray
            )
        )

        // 📝 NOTAS → CreateNoteScreen
        NavigationBarItem(
            selected = false,
            onClick = onAddNoteClick,
            icon = {
                Icon(
                    painterResource(id = R.drawable.notas_icon),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.DarkGray
            )
        )

        // 😄 MOOD / EMOCIONES
        NavigationBarItem(
            selected = false,
            onClick = onMoodClick,
            icon = {
                Icon(
                    painterResource(id = R.drawable.mood_icon),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.DarkGray
            )
        )

        // 📌 TAREAS → CreateTaskScreen
        NavigationBarItem(
            selected = false,
            onClick = onAddTaskClick,
            icon = {
                Icon(
                    painterResource(id = R.drawable.task_icon),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.DarkGray
            )
        )

        // ⚙️ CONFIGURACIÓN → SettingsScreen
        NavigationBarItem(
            selected = false,
            onClick = onSettingsClick,
            icon = {
                Icon(
                    painterResource(id = R.drawable.confi_icon),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.DarkGray
            )
        )
    }
}
