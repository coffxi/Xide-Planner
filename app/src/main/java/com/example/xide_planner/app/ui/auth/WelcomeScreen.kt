package com.example.xide_planner.app.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xide_planner.app.R
import androidx.compose.ui.draw.clip

@Composable
fun WelcomeScreen(
    onCreateAccountClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF8C8D8),
                        Color(0xFFFADCE6)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {

            // Título
            Text(
                text = "¡Bienvenido! :)",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A4A4A),
                textAlign = TextAlign.Center
            )

            // Subtítulo 1
            Text(
                text = "Un día claro empieza\ncon un buen plan.",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF4A4A4A),
                textAlign = TextAlign.Center
            )

            // Subtítulo 2
            Text(
                text = "Sign in o Sign up, tú eliges.\nXide te espera!",
                fontSize = 16.sp,
                color = Color(0xFF4A4A4A),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Imagen principal
            Image(
                painter = painterResource(id = R.drawable.tareas1),
                contentDescription = "Xide planner",
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botón Crear Cuenta
            Button(
                onClick = onCreateAccountClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE08CBF)),
                shape = RoundedCornerShape(50),
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(
                    "Crear Cuenta",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            // Botón Iniciar Sesión
            Button(
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1C4E8)),
                shape = RoundedCornerShape(50),
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(
                    "Iniciar Sesión",
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}
