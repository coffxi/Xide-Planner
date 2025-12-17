package com.example.xide_planner.app.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _uiState = MutableStateFlow<AuthState>(AuthState.Idle)
    val uiState: StateFlow<AuthState> = _uiState

    init {
        // 🔹 Si el usuario YA está logueado, pasamos directo a Success
        val user = auth.currentUser
        if (user != null) {
            _uiState.value = AuthState.Success
        }
    }

    // 🔹 Cuando la UI presiona "Login con Google"
    fun onGoogleLoginClick() {
        _uiState.value = AuthState.Loading
    }

    // 🔹 Cuando MainActivity nos da el idToken
    fun onGoogleSignInResult(idToken: String) {
        _uiState.value = AuthState.Loading

        val credential = GoogleAuthProvider.getCredential(idToken, null)

        viewModelScope.launch {
            auth.signInWithCredential(credential)
                .addOnSuccessListener { result ->
                    val user = result.user
                    if (user != null) {
                        saveUserToFirestore(user)   // ⭐ SE GUARDA EN FIRESTORE ⭐
                    }
                    _uiState.value = AuthState.Success
                }
                .addOnFailureListener {
                    _uiState.value = AuthState.Error(it.message ?: "Error desconocido")
                }
        }
    }

    // 🔹 Si Google Sign-In falla antes de Firebase
    fun onGoogleSignInError(error: String) {
        _uiState.value = AuthState.Error(error)
    }

    // --------------------------------------------------------
    // ⭐⭐⭐ GUARDA A TU USUARIO EN FIRESTORE ⭐⭐⭐
    // --------------------------------------------------------
    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveUserToFirestore(user: FirebaseUser) {
        val db = FirebaseFirestore.getInstance()

        val userDoc = db.collection("users").document(user.uid)

        val data = mapOf(
            "uid" to user.uid,
            "name" to user.displayName,
            "email" to user.email,
            "photo" to user.photoUrl?.toString()
        )

        userDoc.get().addOnSuccessListener { snapshot ->
            if (!snapshot.exists()) {
                userDoc.set(data) // ✔️ lo crea solo si no existe
            }
        }
    }
}

// 🔹 Estados para controlar la UI
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
}
