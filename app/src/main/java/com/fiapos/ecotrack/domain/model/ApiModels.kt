package com.fiapos.ecotrack.domain.model

data class EstimateRequest(
    val emission_factor: EmissionFactor,
    val parameters: Parameters
)

data class EmissionFactor(
    val activity_id: String,
    val data_version: String = "^8"
)

data class Parameters(
    val distance: Double,
    val distance_unit: String = "km"
)

data class EstimateResponse(
    val co2e: Double,
    val co2e_unit: String
)