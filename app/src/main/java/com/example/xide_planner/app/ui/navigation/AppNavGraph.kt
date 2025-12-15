package com.example.xide_planner.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.xide_planner.app.ui.auth.RegisterScreen
import com.example.xide_planner.app.ui.home.HomeScreen
import com.example.xide_planner.app.viewmodel.AuthState

@Composable
fun AppNavGraph(
    navController: NavHostController,
    authState: AuthState,
    onGoogleClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "register"
    ) {
        composable("register") {
            RegisterScreen(
                state = authState,
                onGoogleClick = onGoogleClick,
                onSuccess = {
                    navController.navigate("home") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen()
        }
    }
}
