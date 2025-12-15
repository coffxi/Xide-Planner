package com.example.xide_planner.app.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.xide_planner.app.R

fun getThemeConfig(themeType: AppThemeType): ThemeConfig {
    return when (themeType) {

        AppThemeType.PINK -> ThemeConfig(
            primaryColor = Color(0xFFF697B3),
            onPrimaryColor = Color(0xFFA44683),
            borderColor = Color(0xFFFFE6F4),
            backgroundImageRes = R.drawable.bg_pink
        )

        AppThemeType.BLUE -> ThemeConfig(
            primaryColor = Color(0xFF9EC5FE),
            onPrimaryColor = Color(0xFF1D4ED8),
            borderColor = Color(0xFFDDEAFE),
            backgroundImageRes = R.drawable.bg_blue
        )

        AppThemeType.YELLOW -> ThemeConfig(
            primaryColor = Color(0xFFFFE082),
            onPrimaryColor = Color(0xFF8D6E00),
            borderColor = Color(0xFFFFF3C4),
            backgroundImageRes = R.drawable.bg_yellow
        )
    }
}
