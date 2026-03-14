package com.fiapos.ecotrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    OnboardingScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    ECOTRACKTheme {
        OnboardingScreen(
            viewModel = OnboardingViewModel()
        )
    }
}