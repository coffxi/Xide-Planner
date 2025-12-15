package com.example.xide_planner.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xide_planner.app.ui.auth.RegisterScreen
import com.example.xide_planner.app.ui.tasks.CreateTaskScreen
import com.example.xide_planner.app.viewmodel.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val authViewModel: AuthViewModel = viewModel()
            val state = authViewModel.uiState.collectAsState().value

            RegisterScreen(
                state = state,
                onGoogleClick = {
                    authViewModel.onGoogleLoginClick()
                }
            )
            CreateTaskScreen()
        }
    }
}
