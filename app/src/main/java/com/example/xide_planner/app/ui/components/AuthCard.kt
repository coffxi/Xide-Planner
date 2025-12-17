package com.example.xide_planner.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.ColumnScope

@Composable
fun AuthCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.92f)              // ✔️ tamaño exacto como en el mockup
            .padding(bottom = 0.dp),
        shape = RoundedCornerShape(48.dp),    // ✔️ borde más grande
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp          // ✔️ sombra fuerte como en la imagen
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 36.dp) // ✔️ padding igual al mockup
                .fillMaxWidth(),
            content = content
        )
    }
}

