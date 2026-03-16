package com.fiapos.ecotrack.ui.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fiapos.ecotrack.viewmodel.FoodImpactViewModel
import com.fiapos.ecotrack.viewmodel.FoodResult

@Composable
fun FoodImpactScreen(
    modifier: Modifier = Modifier
) {

    val viewModel: FoodImpactViewModel = viewModel()
    val result by viewModel.result.collectAsState()
    var meat by remember { mutableStateOf(3) }
    var vegetables by remember { mutableStateOf(3) }
    var processed by remember { mutableStateOf(1) }
    val scrollState = rememberScrollState()

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFA7D7B8),
            Color(0xFFBFE3D0))
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6)))
    {

        HeaderSection(gradient)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Selecione a frequência de consumo de cada grupo alimentar por semana:",
                fontSize = 14.sp,
                color = Color(0xFF6B7280)
            )
            Spacer(modifier = Modifier.height(16.dp))

            FoodCard(
                emoji = "🥩",
                title = "Carne Vermelha / Frango",
                selectedColor = Color(0xFFF97316),
                onValueChange = { meat = it })
            Spacer(modifier = Modifier.height(16.dp))

            FoodCard(
                emoji = "🥗",
                title = "Vegetais e Frutas",
                selectedColor = Color(0xFF22C55E),
                onValueChange = { vegetables = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            FoodCard(
                emoji = "🏭",
                title = "Industrializados / Processados",
                selectedColor = Color(0xFF8B5CF6),
                onValueChange = { processed = it }
            )
            Spacer(modifier = Modifier.height(24.dp))

            CalculateButton {
                viewModel.calculate(
                    meat = meat,
                    vegetables = vegetables,
                    processed = processed
                )

            }

            Spacer(modifier = Modifier.height(24.dp))
            result?.let { ResultCard(it) }
            Spacer(modifier = Modifier.height(40.dp))
            BottomNavigationBar()
        }
    }
}

@Composable
fun HeaderSection(gradient: Brush) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradient)
            .padding(20.dp)
    ) {

        Text(
            text = "‹ Voltar",
            color = Color(0xFF166534),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("🥦", fontSize = 32.sp)
            Spacer(modifier = Modifier.width(10.dp))

            Column {

                Text(
                    text = "Calculadora",
                    color = Color(0xFF15803D),
                    fontSize = 14.sp
                )
                Text(
                    text = "Impacto da Alimentação",
                    color = Color(0xFF14532D),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun FoodCard(
    emoji: String,
    title: String,
    selectedColor: Color,
    onValueChange: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(emoji, fontSize = 28.sp)
                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = title,
                    fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(14.dp))

            FrequencyButtons(selectedColor, onValueChange)
        }
    }
}

@Composable
fun FrequencyButtons(
    selectedColor: Color,
    onValueChange: (Int) -> Unit
) {
    var selected by remember { mutableStateOf("3-4") }
    val options = listOf(
        "0" to 0,
        "1-2" to 1,
        "3-4" to 3,
        "5-6" to 5,
        "7" to 7
    )

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                val isSelected = selected == option.first
                Box(
                    modifier = Modifier
                        .background(
                            if (isSelected) selectedColor else Color(0xFFE5E7EB),
                            RoundedCornerShape(20.dp)
                        )
                        .clickable {
                            selected = option.first
                            onValueChange(option.second)
                        }
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = option.first,
                        color = if (isSelected) Color.White else Color(0xFF6B7280)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "dias/semana",
                fontSize = 10.sp,
                color = Color(0xFF9CA3AF))
            Text(
                text = "todos os dias",
                fontSize = 10.sp,
                color = Color(0xFF9CA3AF)
            )
        }
    }
}

@Composable
fun CalculateButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF16A34A)
        )
    ) {
        Text(
            text = "Calcular Emissão",
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

@Composable
fun ResultCard(result: FoodResult) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {

        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF16A34A))
                    .padding(20.dp)
            ) {

                Text("Emissão alimentar mensal", color = Color.White)
                Spacer(modifier = Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = result.total.toInt().toString(),
                        fontSize = 40.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("kg CO₂", color = Color.White)
                }

                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    color = Color(0xFF34D399),
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
                    .background(Color(0xFFE6F4EA))
                    .padding(16.dp)
            ) {

                Text("🌱")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = result.recommendation,
                    color = Color(0xFF166534)
                )
            }
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

@Composable
fun BottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Text("🏠") },
            label = { Text("Início") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Text("🚗") },
            label = { Text("Transporte") }
        )

        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Text("🥦") },
            label = { Text("Alimentação") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Text("📊") },
            label = { Text("Histórico") }
        )
    }
}