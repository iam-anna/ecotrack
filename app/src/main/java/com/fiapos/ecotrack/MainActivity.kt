package com.fiapos.ecotrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fiapos.ecotrack.ui.screen.OnboardingScreen
import com.fiapos.ecotrack.ui.theme.ECOTRACKTheme
import com.fiapos.ecotrack.viewmodel.OnboardingViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ECOTRACKTheme {
                val viewModel: OnboardingViewModel = viewModel()

                OnboardingScreen(viewModel = viewModel)
            }
        }
    }
}