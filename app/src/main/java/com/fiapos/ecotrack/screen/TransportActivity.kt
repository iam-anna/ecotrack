package com.fiapos.ecotrack

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.fiapos.ecotrack.controller.MainController
import com.fiapos.ecotrack.service.MainService
import com.fiapos.ecotrack.ui.EcotrackColor
import com.fiapos.ecotrack.ui.components.Header
import com.fiapos.ecotrack.ui.components.PersonalizedButton
import kotlinx.coroutines.*

@Composable
fun TransportActivity(controller: MainController, service: MainService) {
    var km by remember { mutableStateOf("") }
    var transport by remember { mutableStateOf("car") }
    var result by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
        Header(
            title = "Impacto do Transporte",
            subtitle = "Calculadora",
            emoji = "🚗",
            action = { controller.goToHome() },
            color = EcotrackColor.BLUE
        )

        Column(modifier = Modifier
            .padding(
                top = 20.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 42.dp
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            KilometerInput(
                value = km,
                onValueChange = { km = it }
            )

            TransportSelectionContainer(
                selected = transport,
                onSelect = { transport = it }
            )

            TransportCallout()

            PersonalizedButton("Calcular Impacto") {
                result = "73"
            }

            if (result.isNotEmpty()) {
                ReportTransport(footprint = "84.2")
            }
        }
    }
}

fun getActivityId(type: String): String {

    return when(type){

        "car" ->
            "passenger_vehicle-vehicle_type_medium_car-fuel_source_diesel-engine_size_na-vehicle_age_na-vehicle_weight_na"

        "motorcycle" ->
            "passenger_vehicle-vehicle_type_motorcycle-fuel_source_gasoline-engine_size_na-vehicle_age_na-vehicle_weight_na"

        "bus" ->
            "passenger_vehicle-vehicle_type_bus-fuel_source_na-engine_size_na-vehicle_age_na-vehicle_weight_na"

        "flight" ->
            "passenger_flight-route_type_international-aircraft_type_na-distance_na-class_na-rf_na"

        else ->
            "passenger_vehicle-vehicle_type_medium_car-fuel_source_diesel-engine_size_na-vehicle_age_na-vehicle_weight_na"
    }

}

@Composable
fun TabBarButton(icon: String = "🔴", text: String = "Default", selected: Boolean = false) {
    var selectedColor = if (selected) Color(22, 163, 74) else Color(0xFF0369A1)
    var fontWeight = if (selected) FontWeight(800) else FontWeight(400)

    Column (
        modifier = Modifier
            .height(80.dp)
            .width(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = icon,
            fontSize = 20.sp
        )
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = selectedColor,
            fontWeight = fontWeight
        )
    }
}

@Composable
fun TabBar() {
    Divider(
        color = Color.Gray,
        thickness = 1.dp
    )
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TabBarButton(icon = "🏠", text = "Início")
        TabBarButton(icon = "🚗", text = "Transporte", selected = true)
        TabBarButton(icon = "🥦", text = "Alimentação")
        TabBarButton(icon = "📊", text = "Histórico")
    }
}

@Composable
fun KilometerInput(
    value: String,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Km rodados por dia",
            fontSize = 14.sp,
            color = Color(0xFF374151),
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Ex: 25", color = Color(0x80111827))
            },
            suffix = {
                Text(text = "km/dia", color = Color(0x80111827))
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE2E8F0),
                focusedBorderColor = Color(0xFF0369A1)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
    }
}

@Composable
fun TransportSelectionContainer(
    selected: String,
    onSelect: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Tipo de transporte",
            fontSize = 14.sp,
            color = Color(0xFF374151),
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TransportCard(
                "🚗", "Carro",
                selected == "car",
                modifier = Modifier.weight(1f)
            ) { onSelect("car") }

            TransportCard(
                "🏍️", "Moto",
                selected == "motorcycle",
                modifier = Modifier.weight(1f)
            ) { onSelect("motorcycle") }

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TransportCard(
                "🚌", "Ônibus",
                selected == "bus",
                modifier = Modifier.weight(1f)
            ) { onSelect("bus") }

            TransportCard(
                "✈️", "Avião",
                selected == "flight",
                modifier = Modifier.weight(1f)
            ) { onSelect("flight") }
        }
    }
}

@Composable
fun TransportCard(
    icon: String,
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    val borderColor = if (selected) Color(0xFF38BDF8) else Color(0xFFE2E8F0)
    val backgroundColor = if (selected) Color(0xFFE2E8F0) else Color(0xFFFFFFFF)
    val labelColor = if (selected) Color(0xFF38BDF8) else Color(0xFF374151)


    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(icon, fontSize = 22.sp)

        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = labelColor
        )
    }
}

@Composable
fun TransportCallout() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFFF0F9FF), RoundedCornerShape(14.dp))
        .padding(vertical = 12.dp, horizontal = 16.dp),

        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "ℹ️", fontSize = 14.sp)

        Text(
            text = "Fator de emissão:",
            fontSize = 12.sp,
            color = Color(0xFF0369A1)
        )

        Text(
            text = "0.21 kg CO₂/km",
            fontSize = 12.sp,
            color = Color(0xFF0369A1),
            fontWeight = FontWeight.Bold
        )
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


//@Composable
//fun CalculateSection(
//    km: String,
//    transport: String,
//    onResult: (String) -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 32.dp, vertical = 16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Button(
//            onClick = {
//                val distance = km.toDoubleOrNull()
//                val activityId = getActivityId(transport)
//
//                Log.d("CLIMATIQ", "Transport: $transport")
//                Log.d("CLIMATIQ", "ActivityID: $activityId")
//                Log.d("CLIMATIQ", "Distance: $distance")
//
//                if(distance == null){
//                    onResult("Digite um valor válido")
//                    return@Button
//                }
//
//                CoroutineScope(Dispatchers.IO).launch {
//
//                    try {
//
//                        val request = EstimateRequest(
//                            emission_factor = EmissionFactor(
//                                activity_id = activityId
//                            ),
//                            parameters = Parameters(distance)
//                        )
//
//                        val response = RetrofitClient.api.calculate(request)
//
//                        val resultText = "Emissão: ${response.co2e} kg CO₂"
//
//                        withContext(Dispatchers.Main){
//                            onResult(resultText)
//                        }
//
//                    } catch (e: Exception) {
//                        if(e is retrofit2.HttpException){
//                            val errorBody = e.response()?.errorBody()?.string()
//                            Log.e("CLIMATIQ", "HTTP ERROR: $errorBody")
//                        }
//                        withContext(Dispatchers.Main){
//                            onResult("Erro ao calcular")
//                        }
//
//                    }
//
//                }
//
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF0369A1),
//                contentColor = Color.White,
//            ),
//            shape = RoundedCornerShape(16.dp)
//        ) {
//            Text(
//                "Calcular impacto",
//                fontSize = 16.sp,
//                fontWeight = FontWeight(600)
//            )
//        }
//    }
//}