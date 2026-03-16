package com.fiapos.ecotrack.service

import com.fiapos.ecotrack.viewmodel.FoodResult

class MainService {

    fun calculateFoodService(
        meat: Int,
        vegetables: Int,
        processed: Int
    ): FoodResult {
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

        return FoodResult(
            total = total,
            meat = meatMonthly,
            vegetables = vegMonthly,
            processed = processedMonthly,
            level = level,
            recommendation = recommendation
        )
    }
}