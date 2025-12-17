package com.example.xide_planner.app.data.repository

import com.example.xide_planner.app.data.model.Note
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class NotesRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    suspend fun saveNote(note: Note): Boolean {
        val uid = auth.currentUser?.uid ?: return false

        return try {
            val noteRef = db.collection("users")
                .document(uid)
                .collection("notes")
                .document()

            val noteWithId = note.copy(id = noteRef.id)

            noteRef.set(noteWithId).await()

            true

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    suspend fun getNotes(): List<Note> {
        val uid = auth.currentUser?.uid ?: return emptyList()

        return try {
            val snapshot = db.collection("users")
                .document(uid)
                .collection("notes")
                .get()
                .await()

            snapshot.toObjects(Note::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    suspend fun deleteNote(id: String) {
        val uid = auth.currentUser?.uid ?: return
        db.collection("users").document(uid).collection("notes")
            .document(id).delete().await()
    }
}