package com.example.xide_planner.app.data.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.xide_planner.app.data.model.Note

object NoteRepository {
    // Lista mutable de notas guardadas en memoria
    val notes = mutableStateListOf<Note>()

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun getAllNotes(): List<Note> = notes
}

