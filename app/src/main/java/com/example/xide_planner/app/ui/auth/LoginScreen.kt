package com.example.xide_planner.app.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.xide_planner.app.R
import com.example.xide_planner.app.ui.components.AppBackground
import com.example.xide_planner.app.ui.components.AuthCard
import com.example.xide_planner.app.ui.components.Button1
import com.example.xide_planner.app.ui.theme.AppThemeType
import com.example.xide_planner.app.ui.theme.getThemeConfig
import com.example.xide_planner.app.viewmodel.AuthState

@Composable
fun LoginScreen(
    state: AuthState,
    onGoogleClick: () -> Unit,
    onSuccess: () -> Unit = {},
    onRegisterClick: () -> Unit
) {
    if (state is AuthState.Success) onSuccess()

    val theme = getThemeConfig(AppThemeType.PINK)

    AppBackground(theme = theme) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            AuthCard(modifier = Modifier.padding(bottom = 16.dp)) {

                // 🌟 TÍTULO
                Text(
                    text = "Iniciar Sesión",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 🌸 SUBTÍTULO ROSITA
                Text(
                    text = "Bienvenido de vuelta, planifiquemos tu día.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Texto extra (más pequeño)
                Text(
                    text = "Accede con Google para continuar.",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Iniciar sesión con:",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(58.dp))

                // ⭐ Imagen Google
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

                // ⭐ BOTÓN Google
                Button1(
                    text = "Continuar con Google",
                    onClick = onGoogleClick,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ⭐ LINEA SEPARADORA
                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                )

                // ⭐ TEXTO FINAL → REGISTRARSE
                Text(
                    text = "¿Aún no tienes una cuenta? Regístrate aquí",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = MaterialTheme.colorScheme.primary,
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Clic en "Registrarse"
                Button(
                    onClick = onRegisterClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    )
                ) {
                    Text("Crear cuenta", color = MaterialTheme.colorScheme.onTertiary)
                }

                // Loading
                if (state is AuthState.Loading)
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                // Error
                if (state is AuthState.Error)
                    Text(
                        text = "Error: ${state.message}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
            }
        }
    }
}
