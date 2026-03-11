package com.fiapos.ecotrack

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.fiapos.ecotrack.ui.theme.ECOTRACKTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.unit.dp

class TransportActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ECOTRACKTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Header()
                    KilometerInput()
                    TransportSelectionContainer()
                    Spacer(modifier = Modifier.weight(1f))
                    CalculateSection()
                    TabBar()
                }
            }
        }
    }
}

val textColors = Color(red = 3, green = 105, blue = 161)

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(color = Color(red = 186, green = 230, blue = 253))
    ) {
        Column {
            Text(
                text = "‹ Voltar",
                color = textColors,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column (
                    modifier = Modifier
                        .height(100.dp)
                        .width(80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "🚗",
                        color = Color(red = 3, green = 105, blue = 161),
                        fontSize = 20.sp,
                        modifier = Modifier
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                            .padding(10.dp)

                    )
                }

                Column (
                    modifier = Modifier.fillMaxWidth().height(100.dp)
                ) {
                    Text(
                        text = "Calculadora",
                        color = textColors,
                        fontSize = 12.sp
                    )

                    Text(
                        text = "Impacto do Transporte",
                        color = textColors,
                        fontWeight = FontWeight(800),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

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
fun KilometerInput() {
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
            value = text,
            onValueChange = { text = it },
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
fun TransportCard(icon: String = "🔴", text: String = "Default", selected: Boolean = false) {
    Column (
        modifier = Modifier
            .border(width = 0.5.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
            .height(120.dp)
            .width(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = icon,
            fontSize = 22.sp
        )
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        Text(
            text = text,
            fontSize = 14.sp,
            color = textColors
        )
    }
}

@Composable
fun TransportSelectionContainer() {
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
            TransportCard("🚗","Carro")
            TransportCard("🏍️", "Moto")
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TransportCard("🚌", "Ônibus")
            TransportCard("✈️", "Avião")
        }
    }
}

@Composable
fun CalculateSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {},
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ECOTRACKTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header()
            KilometerInput()
            TransportSelectionContainer()
            Spacer(modifier = Modifier.weight(1f))
            CalculateSection()
            TabBar()
        }
    }
}