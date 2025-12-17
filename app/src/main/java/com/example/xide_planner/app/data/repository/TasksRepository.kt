package com.example.xide_planner.app.data.repository

import android.util.Log
import com.example.xide_planner.app.data.model.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TasksRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    // ⭐ GUARDAR TAREA
    suspend fun saveTask(task: Task): Boolean {
        val uid = auth.currentUser?.uid
        Log.d("TASKS", "UID = $uid")

        if (uid == null) return false

        return try {
            val taskRef = db.collection("users")
                .document(uid)
                .collection("tasks")
                .document()

            val taskWithId = task.copy(id = taskRef.id)
            taskRef.set(taskWithId).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    // ⭐ LEER TAREAS
    suspend fun getTasks(): List<Task> {
        val uid = auth.currentUser?.uid ?: return emptyList()

        return try {
            db.collection("users")
                .document(uid)
                .collection("tasks")
                .get()
                .await()
                .toObjects(Task::class.java)

        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    // ⭐ BORRAR TAREA
    suspend fun deleteTask(id: String) {
        val uid = auth.currentUser?.uid ?: return

        db.collection("users")
            .document(uid)
            .collection("tasks")
            .document(id)
            .delete()
            .await()
    }
}
