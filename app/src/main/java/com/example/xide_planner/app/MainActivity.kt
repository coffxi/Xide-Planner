package com.example.xide_planner.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xide_planner.app.ui.auth.RegisterScreen
import com.example.xide_planner.app.ui.tasks.CreateTaskScreen
import com.example.xide_planner.app.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.example.xide_planner.app.ui.navigation.AppNavGraph
import androidx.navigation.compose.rememberNavController
import java.lang.Exception   // ⭐ IMPORTANTE

class MainActivity : ComponentActivity() {

    // 🔹 Cliente de Google
    private lateinit var googleSignInClient: GoogleSignInClient

    // 🔹 ViewModel (fuera del onCreate)
    private lateinit var authViewModel: AuthViewModel

    // 🔹 Launcher para recibir el resultado de Google Sign-In
    private val googleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        try {
            val account = task.getResult(Exception::class.java)
            val idToken = account.idToken

            if (idToken != null) {
                authViewModel.onGoogleSignInResult(idToken)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            authViewModel.onGoogleSignInError(e.message ?: "Error desconocido")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ⭐ 1. Configurar Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // viene del JSON
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // ⭐ 2. UI
        setContent {
            authViewModel = viewModel()
            val state = authViewModel.uiState.collectAsState().value
            val navController = rememberNavController()

            AppNavGraph(
                navController = navController,
                authState = state,
                onGoogleClick = {
                    val signInIntent = googleSignInClient.signInIntent
                    googleLauncher.launch(signInIntent)
                }
            )
        }

    }
}
