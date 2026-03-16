package com.fiapos.ecotrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fiapos.ecotrack.TransportActivity
import com.fiapos.ecotrack.controller.MainController
import com.fiapos.ecotrack.screen.HomeScreen
import com.fiapos.ecotrack.screen.OnboardingScreen
import com.fiapos.ecotrack.service.MainService

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val controller = MainController(navController)
    val service = MainService()

    NavHost(
        navController = navController,
        startDestination = Routes.Onboarding.route
    ) {
        composable(Routes.Onboarding.route){
            OnboardingScreen(controller)
        }

        composable(Routes.Home.route){
            HomeScreen(controller)
        }

        composable(Routes.Transport.route) {
            TransportActivity(controller, service)
        }
    }
}