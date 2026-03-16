package com.fiapos.ecotrack.navigation

sealed class Routes(val route: String) {
    object Onboarding : Routes("onboarding")
    object Home : Routes("home")
    object Transport : Routes("transport")
}