package com.fiapos.ecotrack

import android.R
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.unit.dp
import com.fiapos.ecotrack.controller.MainController
import com.fiapos.ecotrack.domain.model.EmissionFactor
import com.fiapos.ecotrack.domain.model.EstimateRequest
import com.fiapos.ecotrack.domain.model.Parameters
import com.fiapos.ecotrack.service.RetrofitClient
import com.fiapos.ecotrack.ui.EcotrackColor
import com.fiapos.ecotrack.ui.components.Header
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.*

@Composable
fun TransportActivity(controller: MainController) {
    var km by remember { mutableStateOf("") }
    var transport by remember { mutableStateOf("car") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(
            title = "Impacto do Transporte",
            subtitle = "Calculadora",
            emoji = "🚗",
            action = { controller.goToHome() },
            color = EcotrackColor.BLUE
        )
        KilometerInput(
            value = km,
            onValueChange = { km = it }
        )
        TransportSelectionContainer(
            selected = transport,
            onSelect = { transport = it }
        )
        Spacer(modifier = Modifier.weight(1f))
        CalculateSection(
            km = km,
            transport = transport,
            onResult = { result = it }
        )

        if (result.isNotEmpty()) {
            Text(
                text = result,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        TabBar()
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

val textColors = Color(red = 3, green = 105, blue = 161)



@Composable
fun TabBarButton(icon: String = "🔴", text: String = "Default", selected: Boolean = false) {
    var selectedColor = if (selected) Color(22, 163, 74) else textColors
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Km rodados por dia",
            fontSize = 14.sp,
            color = textColors,
            fontWeight = FontWeight(600)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Ex: 25", color = Color.Gray)
            },
            suffix = {
                Text(text = "km/dia", color = Color(0xFF718096))
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = textColors,
                focusedBorderColor = textColors
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
    }
}

@Composable
fun TransportCard(
    icon: String,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    val borderColor = if (selected) Color(22,163,74) else Color.Gray
    val backgroundColor = if (selected) Color(220,252,231) else Color.White

    Column(
        modifier = Modifier
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .height(120.dp)
            .width(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(icon, fontSize = 22.sp)

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = text,
            fontSize = 14.sp,
            color = textColors
        )
    }
}

@Composable
fun TransportSelectionContainer(
    selected: String,
    onSelect: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Tipo de transporte",
            fontSize = 14.sp,
            color = textColors,
            fontWeight = FontWeight(600)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TransportCard("🚗","Carro", selected == "car") { onSelect("car") }
            TransportCard("🏍️", "Moto", selected == "motorcycle") { onSelect("motorcycle") }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TransportCard("🚌", "Ônibus", selected == "bus") { onSelect("bus") }
            TransportCard("✈️", "Avião", selected == "flight") { onSelect("flight") }
        }
    }
}

@Composable
fun CalculateSection(
    km: String,
    transport: String,
    onResult: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                val distance = km.toDoubleOrNull()
                val activityId = getActivityId(transport)

                Log.d("CLIMATIQ", "Transport: $transport")
                Log.d("CLIMATIQ", "ActivityID: $activityId")
                Log.d("CLIMATIQ", "Distance: $distance")

                if(distance == null){
                    onResult("Digite um valor válido")
                    return@Button
                }

                CoroutineScope(Dispatchers.IO).launch {

                    try {

                        val request = EstimateRequest(
                            emission_factor = EmissionFactor(
                                activity_id = activityId
                            ),
                            parameters = Parameters(distance)
                        )

                        val response = RetrofitClient.api.calculate(request)

                        val resultText = "Emissão: ${response.co2e} kg CO₂"

                        withContext(Dispatchers.Main){
                            onResult(resultText)
                        }

                    } catch (e: Exception) {
                        if(e is retrofit2.HttpException){
                            val errorBody = e.response()?.errorBody()?.string()
                            Log.e("CLIMATIQ", "HTTP ERROR: $errorBody")
                        }
                        withContext(Dispatchers.Main){
                            onResult("Erro ao calcular")
                        }

                    }

                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = textColors,
                contentColor = Color.White,
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                "Calcular impacto",
                fontSize = 16.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}