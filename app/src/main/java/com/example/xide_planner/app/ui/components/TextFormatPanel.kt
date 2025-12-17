package com.example.xide_planner.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFormatPanel(
    isBold: Boolean,
    isItalic: Boolean,
    isUnderline: Boolean,
    onBoldClick: () -> Unit,
    onItalicClick: () -> Unit,
    onUnderlineClick: () -> Unit,
    onBulletsClick: () -> Unit,
    onNumbersClick: () -> Unit,
    onImageClick: () -> Unit,
    selectedTextColor: Color,
    onTextColorSelected: (Color) -> Unit,
    selectedHighlightColor: Color,
    onHighlightColorSelected: (Color) -> Unit
) {

    val containerColor = Color(0xFFFFC2E3)

    val textColors = listOf(
        Color.Black,
        Color(0xFFD1ACC0),
        Color(0xFF7453A2),
        Color(0xFF4D79C5),
        Color(0xFF649964),
        Color(0xFFABA043),
        Color(0xFFD58462)
    )

    val highlightColors = listOf(
        Color.Transparent,
        Color(0xFFFFD9EE),
        Color(0xFFE0CBFF),
        Color(0xFFD3E3FF),
        Color(0xFFD9FFD9),
        Color(0xFFFFF8BE),
        Color(0xFFFFE0D2)
    )

    Surface(
        color = Color(0xFFFFF1FC),
        tonalElevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            RoundedContainer(containerColor) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FormatIconButton(Icons.Default.FormatBold, isBold, onBoldClick)
                    FormatIconButton(Icons.Default.FormatItalic, isItalic, onItalicClick)
                    FormatIconButton(Icons.Default.FormatUnderlined, isUnderline, onUnderlineClick)
                    FormatIconButton(Icons.Default.FormatListBulleted, false, onBulletsClick)
                    FormatIconButton(Icons.Default.FormatListNumbered, false, onNumbersClick)
                    FormatIconButton(Icons.Default.Image, false, onImageClick)
                }
            }

            Text("Color del texto", fontSize = 12.sp, color = Color.Gray)

            RoundedContainer(containerColor) {
                ColorRow(textColors, selectedTextColor, onTextColorSelected)
            }

            Text("Marcador", fontSize = 12.sp, color = Color.Gray)

            RoundedContainer(containerColor) {
                ColorRow(
                    colors = highlightColors,
                    selectedColor = selectedHighlightColor,
                    onColorSelected = onHighlightColorSelected
                )
            }
        }
    }
}

@Composable
private fun RoundedContainer(
    backgroundColor: Color,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .padding(vertical = 10.dp, horizontal = 8.dp)
    ) {
        content()
    }
}

@Composable
private fun FormatIconButton(
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(40.dp)
            .background(
                if (selected) Color(0xFFE9A7CB) else Color.Transparent,
                CircleShape
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF8B1D3D),
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun ColorRow(
    colors: List<Color>,
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(color)
                    .border(
                        width = if (color == selectedColor) 2.dp else 0.dp,
                        color = Color(0xFFFFF3FB),
                        shape = CircleShape
                    )
                    .clickable { onColorSelected(color) }
            )
        }
    }
}
