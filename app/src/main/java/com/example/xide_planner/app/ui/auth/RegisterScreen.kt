package com.example.xide_planner.app.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.xide_planner.app.viewmodel.AuthState

@Composable
fun RegisterScreen(
    state: AuthState,
    onGoogleClick: () -> Unit,
    onSuccess: () -> Unit = {} // ← nuevo parámetro
) {

    // 🔹 Si el login fue exitoso: navegamos
    if (state is AuthState.Success) {
        onSuccess()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Crear cuenta", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Organiza tus planes con Xide", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onGoogleClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = state !is AuthState.Loading // desactivar mientras carga
        ) {
            Text(text = "Registrarse con Google")
        }

        // 🔹 Loader
        if (state is AuthState.Loading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()
        }

        // 🔹 Error
        if (state is AuthState.Error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Error: ${state.message}",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
