package com.fiapos.ecotrack.service

import com.fiapos.ecotrack.model.EstimateRequest
import com.fiapos.ecotrack.model.EstimateResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Service {

    @Headers(
        "Authorization: Bearer XAVZ30BE7S4H5DQ1VSM9ZH8Y14",
        "Content-Type: application/json"
    )
    @POST("data/v1/estimate")
    suspend fun calculate(
        @Body request: EstimateRequest
    ): EstimateResponse
}