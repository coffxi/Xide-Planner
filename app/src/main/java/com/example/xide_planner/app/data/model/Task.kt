package com.example.xide_planner.app.data.model

data class Task(
    val id: String = "",
    val name: String = "",
    val colorIndex: Int = 0,
    val dateMillis: Long? = null,
    val repeat: String = "No repetir",
    val time: String = "En cualquier momento",
    val reminder: String = "Sin aviso",
    val tag: String = "Sin etiqueta"
)
