package com.example.xide_planner.app.data.model

data class Note(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
