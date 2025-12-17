package com.example.xide_planner.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.xide_planner.app.ui.auth.RegisterScreen
import com.example.xide_planner.app.ui.auth.LoginScreen
import com.example.xide_planner.app.ui.home.HomeScreen
import com.example.xide_planner.app.ui.auth.WelcomeScreen
import com.example.xide_planner.app.viewmodel.AuthState

@Composable
fun AppNavGraph(
    navController: NavHostController,
    authState: AuthState,
    onGoogleClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {

        // ⭐ WELCOME
        composable("welcome") {
            WelcomeScreen(
                onCreateAccountClick = { navController.navigate("register") },
                onLoginClick = { navController.navigate("login") }
            )
        }

        // ⭐ LOGIN
        composable("login") {
            LoginScreen(
                state = authState,
                onGoogleClick = onGoogleClick,
                onSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // ⭐ REGISTER
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

        // ⭐ HOME (después del login/registro)
        composable("home") {
            HomeScreen()
        }
    }
}
