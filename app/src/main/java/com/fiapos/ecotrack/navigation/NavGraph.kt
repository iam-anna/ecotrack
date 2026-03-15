package com.fiapos.ecotrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fiapos.ecotrack.controller.MainController
import com.fiapos.ecotrack.screen.HomeScreen
import com.fiapos.ecotrack.screen.OnboardingScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val controller = MainController(navController)

    NavHost(
        navController = navController,
        startDestination = Routes.Onboarding.route
    ) {
        composable(Routes.Onboarding.route){
            OnboardingScreen(controller)
        }

        composable(Routes.Home.route){
            HomeScreen()
        }
    }
}