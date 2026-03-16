package com.fiapos.ecotrack.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Dados "mocados". Uso de serviç externo já existente (API) na tela de Transportes
data class FoodResult(
    val total: Double,
    val meat: Double,
    val vegetables: Double,
    val processed: Double,
    val level: String,
    val recommendation: String
)

class FoodImpactViewModel : ViewModel() {
    private val _result = MutableStateFlow<FoodResult?>(null)
    val result: StateFlow<FoodResult?> = _result

    fun calculate(
        meat: Int,
        vegetables: Int,
        processed: Int
    ) {
        val meatFactor = 5.4
        val vegFactor = 0.4
        val processedFactor = 1.0
        val meatMonthly = meat * meatFactor * 4
        val vegMonthly = vegetables * vegFactor * 4
        val processedMonthly = processed * processedFactor * 4
        val total = meatMonthly + vegMonthly + processedMonthly
        val level = when {
            total < 40 -> "baixo"
            total < 90 -> "médio"
            else -> "alto"
        }

        val recommendation = when {
            meat > 4 ->
                "Reduzir carne em 2 dias por semana pode diminuir até 20% da sua emissão alimentar."
            processed > 4 ->
                "Diminuir alimentos industrializados pode reduzir até 10% da sua pegada de carbono."
            vegetables < 3 ->
                "Aumentar consumo de vegetais ajuda a reduzir impacto ambiental."
            else ->
                "Seu consumo alimentar já está relativamente sustentável. Continue assim!"
        }

        _result.value = FoodResult(
            total = total,
            meat = meatMonthly,
            vegetables = vegMonthly,
            processed = processedMonthly,
            level = level,
            recommendation = recommendation)
    }
}