package com.fiapos.ecotrack.ui.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fiapos.ecotrack.controller.MainController
import com.fiapos.ecotrack.service.MainService
import com.fiapos.ecotrack.ui.EcotrackColor
import com.fiapos.ecotrack.ui.components.Header
import com.fiapos.ecotrack.ui.components.PersonalizedButton
import com.fiapos.ecotrack.viewmodel.FoodResult

@Composable
fun FoodScreen(controller: MainController, service: MainService) {
    var result by remember { mutableStateOf<FoodResult?>(null) }
    var meat by remember { mutableStateOf(3) }
    var vegetables by remember { mutableStateOf(3) }
    var processed by remember { mutableStateOf(1) }
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
        Header(
            title = "Impacto da Alimentação",
            subtitle = "Calculadora",
            emoji = "🥦",
            action = { controller.goToHome() },
            color = EcotrackColor.GREEN
        )

        Column(modifier = Modifier
            .padding(
                top = 20.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 80.dp
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Text(
                text = "Selecione a frequência de consumo de cada grupo alimentar por semana:",
                fontSize = 12.sp,
                color = Color(0xFF6B7280)
            )

            FoodCard(
                emoji = "🥩",
                title = "Carne Vermelha / Frango",
                selectedColor = Color(0xFFF97316),
                value = meat,
                onValueChange = { meat = it }
            )

            FoodCard(
                emoji = "🥗",
                title = "Vegetais e Frutas",
                selectedColor = Color(0xFF22C55E),
                value = vegetables,
                onValueChange = { vegetables = it }
            )

            FoodCard(
                emoji = "🏭",
                title = "Industrializados / Processados",
                selectedColor = Color(0xFF8B5CF6),
                value = processed,
                onValueChange = { processed = it }
            )

            PersonalizedButton("Calcular Impacto") {
                result = service.calculateFoodService(meat, vegetables, processed)
            }

            result?.let { ResultCard(it) }
        }
    }
}

@Composable
fun FoodCard(
    emoji: String,
    title: String,
    selectedColor: Color,
    value: Int,
    onValueChange: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(emoji, fontSize = 24.sp)

                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF374151)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            FrequencyButtons(
                selectedColor = selectedColor,
                value = value,
                onValueChange = onValueChange
            )
        }
    }
}

@Composable
fun FrequencyButtons(
    selectedColor: Color,
    value: Int,
    onValueChange: (Int) -> Unit
) {

    val options = listOf(
        "0" to 0,
        "1-2" to 1,
        "3-4" to 3,
        "5-6" to 5,
        "7" to 7
    )

    Column {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                val isSelected = value == option.second

                Box(
                    modifier = Modifier
                        .background(
                            if (isSelected) selectedColor else Color(0xFFE5E7EB),
                            RoundedCornerShape(20.dp)
                        )
                        .clickable {
                            onValueChange(option.second)
                        }
                        .padding(vertical = 8.dp)
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = option.first,
                        color = if (isSelected) Color.White else Color(0xFF6B7280),
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "dias/semana",
                fontSize = 10.sp,
                color = Color(0xFF9CA3AF)
            )

            Text(
                text = "todos os dias",
                fontSize = 10.sp,
                color = Color(0xFF9CA3AF)
            )
        }
    }
}

@Composable
fun ReportTransport(footprint: String) {
    Column(modifier = Modifier.background(
        color = Color(0xFFF0F9FF),
        shape = RoundedCornerShape(24.dp)
    )){
        Column (modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0EA5E9),
                        Color(0xFF0369A1)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(300f, 300f) // cria o efeito diagonal ~135°
                ),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .padding(all = 20.dp),
        ) {
            Text(
                text = "Emissão mensal estimada",
                fontSize = 12.sp,
                color = Color(0xCCFFFFFF)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = footprint,
                    fontSize = 46.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFFFFF)
                )

                Text(
                    text = "kg CO₂",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xCCFFFFFF)
                )
            }

            Text(
                text = "por mês · 11 km/dia",
                fontSize = 12.sp,
                color = Color(0xCCFFFFFF)
            )
        }

        Row (modifier = Modifier.padding(all = 20.dp)) {
            Text(text = "🌳", fontSize = 24.sp,)
            Text(
                text = "Equivale a plantar 5 árvores para compensar esta emissão mensal",
                fontSize = 14.sp,
                color = Color(0xFF0C4A6E)
            )
        }
    }
}


@Composable
fun ResultCard(result: FoodResult) {
    Column (modifier = Modifier.background(
        color = Color(0xFFF0F9FF),
        shape = RoundedCornerShape(24.dp)
    )) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF22C55E),
                        Color(0xFF059669)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(300f, 300f)
                ),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .padding(20.dp)
        ) {
            Text(
                text = "Emissão alimentar mensal",
                fontSize = 12.sp,
                color = Color(0xCCFFFFFF)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = result.total.toInt().toString(),
                    fontSize = 46.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFFFFF)
                )

                Text(
                    text = "kg CO₂",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xCCFFFFFF)
                )
            }

                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    color = Color(0x33FFFFFF),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Nível: ${result.level}",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color.White
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(20.dp)
            ) {
                Text("Detalhamento")
                Spacer(modifier = Modifier.height(16.dp))

                ImpactRow("🥩", "Carne", result.meat, Color(0xFFF97316))
                Spacer(modifier = Modifier.height(10.dp))
                ImpactRow("🥗", "Vegetais", result.vegetables, Color(0xFF22C55E))
                Spacer(modifier = Modifier.height(10.dp))
                ImpactRow("🏭", "Industrializados", result.processed, Color(0xFF8B5CF6))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFE6F4EA),
                        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                    )
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 24.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(text = "🌱", fontSize = 24.sp,)
                Text(
                    text = result.recommendation,
                    fontSize = 14.sp,
                    color = Color(0xFF0C4A6E)
                )
            }
        }
}

@Composable
fun ImpactRow(
    emoji: String,
    label: String,
    value: Double,
    color: Color
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(emoji)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = label,
                modifier = Modifier.weight(1f),
                color = Color(0xFF6B7280))
            Text(
                text = "${value.toInt()} kg",
                color = color
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = (value / 100).toFloat().coerceIn(0f, 1f),
            color = color,
            trackColor = Color(0xFFE5E7EB),
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
        )
    }
}