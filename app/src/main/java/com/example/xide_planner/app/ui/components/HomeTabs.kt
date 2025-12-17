package com.example.xide_planner.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.xide_planner.app.ui.home.HomeSection
import androidx.compose.foundation.shape.RoundedCornerShape
@Composable
fun HomeTabs(selected: HomeSection, onSelect: (HomeSection) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TabItem(
            text = "Tareas",
            isSelected = selected == HomeSection.TASKS,
            onClick = { onSelect(HomeSection.TASKS) }
        )
        TabItem(
            text = "Notas",
            isSelected = selected == HomeSection.NOTES,
            onClick = { onSelect(HomeSection.NOTES) }
        )
        TabItem(
            text = "Emociones",
            isSelected = selected == HomeSection.EMOTIONS,
            onClick = { onSelect(HomeSection.EMOTIONS) }
        )
    }
}

@Composable
fun TabItem(text: String, isSelected: Boolean, onClick: () -> Unit) {

    val background = if (isSelected) Color(0xFFF7CFE7) else Color(0xFFF4F4F4)
    val textColor = if (isSelected) Color(0xFFB75C93) else Color(0x65767676)

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50))
            .background(background)
            .shadow(4.dp, RoundedCornerShape(50))
            .clickable { onClick() }
            .padding(horizontal = 26.dp, vertical = 12.dp)
    ) {
        Text(text = text, color = textColor)
    }
}
