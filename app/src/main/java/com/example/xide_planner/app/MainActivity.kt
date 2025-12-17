package com.example.xide_planner.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController

import com.example.xide_planner.app.R
import com.example.xide_planner.app.ui.navigation.AppNavGraph
import com.example.xide_planner.app.ui.components.AppBackground
import com.example.xide_planner.app.ui.theme.AppThemeType
import com.example.xide_planner.app.ui.theme.XidePlannerTheme
import com.example.xide_planner.app.ui.theme.getThemeConfig
import com.example.xide_planner.app.viewmodel.AuthViewModel

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity : ComponentActivity() {

    // Google Sign-In
    private lateinit var googleSignInClient: GoogleSignInClient

    // ViewModel
    private lateinit var authViewModel: AuthViewModel

    // Google Sign-In Result Launcher
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

        // ⭐ Configurar Google Sign-In desde firebase.json
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {

            authViewModel = viewModel()
            val state = authViewModel.uiState.collectAsState().value

            val navController = rememberNavController()

            var currentTheme by remember { mutableStateOf(AppThemeType.PINK) }
            val themeConfig = getThemeConfig(currentTheme)

                AppBackground(theme = themeConfig) {
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
    }

