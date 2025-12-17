package com.example.xide_planner.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
@Composable
fun DashboardCard(title: String, description: String, icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFBD6EB), RoundedCornerShape(28.dp))
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description, color = Color(0xFF747474))
        }

        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(90.dp)
        )
    }
}

