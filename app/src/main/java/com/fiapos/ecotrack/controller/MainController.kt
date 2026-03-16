package com.fiapos.ecotrack.controller

import androidx.navigation.NavHostController
import com.fiapos.ecotrack.navigation.Routes

class MainController(
    private val navController: NavHostController
){
    fun startApp() {
        navController.navigate(Routes.Home.route) {
            popUpTo(Routes.Onboarding.route) { inclusive = true }
        }
    }

    fun goToHome() {
        navController.navigate(Routes.Home.route)
    }

    fun goToTransport() {
        navController.navigate(Routes.Transport.route)
    }
}