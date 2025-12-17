package com.example.xide_planner.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xide_planner.app.ui.auth.RegisterScreen
import com.example.xide_planner.app.ui.components.AppBackground
import com.example.xide_planner.app.ui.tasks.CreateTaskScreen
import com.example.xide_planner.app.ui.theme.AppThemeType
import com.example.xide_planner.app.ui.theme.getThemeConfig
import com.example.xide_planner.app.viewmodel.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xide_planner.app.ui.notes.CreateNoteScreen
import com.example.xide_planner.app.ui.theme.XidePlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var currentTheme by remember {
                mutableStateOf(AppThemeType.PINK) }
            val themeConfig = getThemeConfig(currentTheme)

            AppBackground(theme = themeConfig) {

                // aquí va tu navegación o pantalla
                CreateNoteScreen()
            }
        }
    }
}
