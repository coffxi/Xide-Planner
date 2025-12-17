package com.example.xide_planner.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.xide_planner.app.ui.components.BottomBar
import com.example.xide_planner.app.ui.components.DashboardCard
import com.example.xide_planner.app.ui.components.HomeTabs
import com.example.xide_planner.app.R

@Composable
fun HomeScreen(
    onAddTask: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(HomeSection.TASKS) }

    Scaffold(
        bottomBar = { BottomBar(onAddTask = onAddTask) },
        containerColor = Color(0xFFF6E8F1)
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6E8F1))
                .padding(padding)
                .padding(16.dp)
        ) {

            // ---------- TABS ----------
            HomeTabs(
                selected = selectedTab,
                onSelect = { selectedTab = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ---------- CONTENIDO POR TAB ----------
            when (selectedTab) {

                HomeSection.TASKS -> {
                    DashboardCard(
                        title = "Reporte semanal",
                        description = "No hay eventos próximos",
                        icon = R.drawable.tareas1   // ← pon el ícono correcto
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DashboardCard(
                        title = "Tareas pendientes",
                        description = "No hay tareas pendientes",
                        icon = R.drawable.tareas1    // ← pon el ícono correcto
                    )
                }

                HomeSection.NOTES -> {
                    DashboardCard(
                        title = "Notas",
                        description = "No hay notas registradas",
                        icon = R.drawable.tareas1     // ← tu ícono
                    )
                }

                HomeSection.EMOTIONS -> {
                    DashboardCard(
                        title = "Emociones",
                        description = "Aún no registras una emoción",
                        icon = R.drawable.tareas1 // ← tu ícono
                    )
                }
            }
        }
    }
}

enum class HomeSection { TASKS, NOTES, EMOTIONS }
