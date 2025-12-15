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
fun RegisterScreen(
    state: AuthState,
    onGoogleClick: () -> Unit,
    onSuccess: () -> Unit = {}
) {
    if (state is AuthState.Success) onSuccess()

    val theme = getThemeConfig(AppThemeType.PINK)

    AppBackground(theme = theme) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 48.dp),   // ✔️ baja el card aún más
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))  // ✔️ empuja el card hacia abajo

            AuthCard(
                modifier = Modifier
                    .padding(bottom = 16.dp)       // ✔️ separación suave del borde
            ) {

                // ——— TITULO ———
                Text(
                    text = "Crear Cuenta",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally) // ✔️ centrado
                )

                Spacer(modifier = Modifier.height(12.dp))

                // ——— SUBTÍTULO ———
                Text(
                    text = "Organización inteligente para mentes creativas.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.CenterHorizontally) // ✔️ centrado
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "¿Listo para continuar?",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally) // ✔️ centrado
                )
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    text = "Registrarse con.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.CenterHorizontally) // ✔️ centrado
                )
                Spacer(modifier = Modifier.height(58.dp))

                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

                // ——— BOTÓN ———
                Button1(
                    text = "Registrarse con Google",
                    onClick = onGoogleClick,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (state is AuthState.Loading)
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))

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
