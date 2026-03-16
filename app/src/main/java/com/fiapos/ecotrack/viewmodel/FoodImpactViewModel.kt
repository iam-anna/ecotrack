package com.fiapos.ecotrack.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class FoodResult(
    val total: Double,
    val meat: Double,
    val vegetables: Double,
    val processed: Double,
    val level: String,
    val recommendation: String
)