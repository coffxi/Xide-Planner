package com.example.xide_planner.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xide_planner.app.data.model.Note
import com.example.xide_planner.app.data.repository.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel() {

    private val repo = NotesRepository()

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    fun loadNotes() {
        viewModelScope.launch {
            _notes.value = repo.getNotes()
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            repo.deleteNote(noteId)
            loadNotes()
        }
    }
}
