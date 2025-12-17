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
    onAddNoteClick: () -> Unit = {}
) {
    val iconSize = 28.dp
    val calendarSize = 32.dp

    NavigationBar(
        containerColor = Color(0xFFF4C9D9),
        tonalElevation = 0.dp,
        modifier = Modifier.height(70.dp)
    ) {

        // HOME
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(painterResource(id = R.drawable.home_icon), null, Modifier.size(iconSize)) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.DarkGray
            )
        )

        // CALENDAR
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(painterResource(id = R.drawable.calendar_icon), null, Modifier.size(calendarSize)) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.DarkGray
            )
        )

        // ⭐ NOTA → CreateNoteScreen
        NavigationBarItem(
            selected = false,
            onClick = onAddNoteClick,
            icon = { Icon(painterResource(id = R.drawable.notas_icon), null, Modifier.size(iconSize)) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.DarkGray
            )
        )

        // EMOCIONES
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(painterResource(id = R.drawable.mood_icon), null, Modifier.size(iconSize)) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.DarkGray
            )
        )

        // ⭐ ACTIVIDAD → CreateTaskScreen
        NavigationBarItem(
            selected = false,
            onClick = onAddTaskClick,
            icon = { Icon(painterResource(id = R.drawable.task_icon), null, Modifier.size(iconSize)) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.DarkGray
            )
        )

        // CONFIGURACIÓN
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(painterResource(id = R.drawable.confi_icon), null, Modifier.size(iconSize)) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.DarkGray,
                unselectedIconColor = Color.DarkGray
            )
        )
    }
}
