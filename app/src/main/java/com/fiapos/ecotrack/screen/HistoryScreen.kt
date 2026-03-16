package com.fiapos.ecotrack.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fiapos.ecotrack.controller.MainController
import com.fiapos.ecotrack.ui.EcotrackColor
import androidx.compose.material3.LinearProgressIndicator
import com.fiapos.ecotrack.ui.components.Header

@Composable
fun HistoryScreen(controller: MainController) {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Header(
            title = "Seu Histórico Ambiental",
            subtitle = "Relatórios",
            emoji = "📊",
            action = { controller.goToHome() },
            color = EcotrackColor.PURPLE
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 80.dp
                )
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                ReportCard(
                    label = "Média mensal",
                    value = "153",
                    valueType = "kg",
                    subtitle = "CO₂ / mês",
                    modifier = Modifier.weight(1f)
                )

                ReportCard(
                    label = "Economizado",
                    value = "25",
                    valueType = "kg",
                    subtitle = "vs. 3 meses atrás",
                    valueColor = Color(0xFF16A34A),
                    modifier = Modifier.weight(1f)
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Breakdown por Categoria",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF374151)
                )

                MonthlyReportCard(
                    month = "Março 2026",
                    totalCO2 = 142,
                    transport = 87,
                    food = 55,
                )

                MonthlyReportCard(
                    month = "Fevereiro 2026",
                    totalCO2 = 154,
                    transport = 95,
                    food = 59,
                )

                MonthlyReportCard(
                    month = "Janeiro 2026",
                    totalCO2 = 147,
                    transport = 91,
                    food = 56,
                )

                MonthlyReportCard(
                    month = "Dezemrbro 2025",
                    totalCO2 = 167,
                    transport = 102,
                    food = 65,
                )

                MonthlyReportCard(
                    month = "Fevereiro 2026",
                    totalCO2 = 154,
                    transport = 95,
                    food = 59,
                )
            }

        }
    }
}

@Composable
fun ReportCard(
    label: String,
    value: String,
    valueType: String,
    valueColor: Color = Color(0xFF111827),
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0xFFF3F4F6),
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {

        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF9CA3AF)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.Bottom) {

            Text(
                text = value,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = valueColor
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = valueType,
                fontSize = 12.sp,
                color = Color(0xFF9CA3AF)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = subtitle,
            fontSize = 12.sp,
            color = Color(0xFF6B7280)
        )
    }
}

@Composable
fun MonthlyReportCard(
    month: String,
    totalCO2: Int,
    transport: Int,
    food: Int,
    modifier: Modifier = Modifier
) {

    val total = transport + food
    val transportProgress = transport.toFloat() / total
    val foodProgress = food.toFloat() / total

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF3F4F6),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp)
    ) {

        Text(
            text = month,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF374151)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Total CO2
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "$totalCO2",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF111827)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "kg CO₂",
                    fontSize = 16.sp,
                    color = Color(0xFF6B7280)
                )
            }

            // Breakdown
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text("🚗 $transport kg", fontSize = 14.sp)
                Text("🥦 $food kg", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Transport
        Text(
            text = "Transporte",
            fontSize = 12.sp,
            color = Color(0xFF6B7280)
        )

        LinearProgressIndicator(
            progress = transportProgress,
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = Color(0xFF38BDF8),
            trackColor = Color(0xFFE5E7EB)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Food
        Text(
            text = "Alimentação",
            fontSize = 12.sp,
            color = Color(0xFF6B7280)
        )

        LinearProgressIndicator(
            progress = foodProgress,
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = Color(0xFF4ADE80),
            trackColor = Color(0xFFE5E7EB)
        )
    }
}