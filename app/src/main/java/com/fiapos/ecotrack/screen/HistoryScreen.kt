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
import androidx.compose.material3.Divider
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
import com.fiapos.ecotrack.ui.components.Header

@Composable
fun HistoryScreen(controller: MainController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Header(
            title = "Seu Histórico Ambiental",
            subtitle = "Relatórios",
            emoji = "📊",
            action = { controller.goToHome() },
            color = EcotrackColor.PURPLE
        )
        ReportSection()
        HistorySection()
        Spacer(modifier = Modifier.weight(1f))
    }
}

//var textColors = Color(76, 29, 149)
//@Composable
//fun Header() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(150.dp)
//    ) {
//        Column {
//            Text(
//                text = "‹ Voltar",
//                color = textColors,
//                fontSize = 16.sp,
//                modifier = Modifier
//                    .padding(horizontal = 20.dp, vertical = 20.dp)
//            )
//
//            Spacer(modifier = Modifier.height(15.dp))
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Column (
//                    modifier = Modifier
//                        .height(100.dp)
//                        .width(80.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Top
//                ) {
//                    Text(
//                        text = "📊",
//                        color = Color(red = 3, green = 105, blue = 161),
//                        fontSize = 20.sp,
//                        modifier = Modifier
//                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
//                            .padding(10.dp)
//
//                    )
//                }
//
//                Column (
//                    modifier = Modifier.fillMaxWidth().height(100.dp)
//                ) {
//                    Text(
//                        text = "Relatórios",
//                        color = textColors,
//                        fontSize = 12.sp
//                    )
//
//                    Text(
//                        text = "Seu Histórico Ambiental",
//                        color = textColors,
//                        fontWeight = FontWeight(800),
//                        fontSize = 20.sp
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun TabBarButton(icon: String = "🔴", text: String = "Default", selected: Boolean = false) {
//    var selectedColor = if (selected) Color(22, 163, 74) else textColors
//    var fontWeight = if (selected) FontWeight(800) else FontWeight(400)
//
//    Column (
//        modifier = Modifier
//            .height(80.dp)
//            .width(80.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = icon,
//            fontSize = 20.sp
//        )
//        Spacer(
//            modifier = Modifier.height(5.dp)
//        )
//        Text(
//            text = text,
//            fontSize = 12.sp,
//            color = selectedColor,
//            fontWeight = fontWeight
//        )
//    }
//}

//@Composable
//fun TabBar() {
//    Divider(
//        color = Color.Gray,
//        thickness = 1.dp
//    )
//    Row (
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        TabBarButton(icon = "🏠", text = "Início")
//        TabBarButton(icon = "🚗", text = "Transporte")
//        TabBarButton(icon = "🥦", text = "Alimentação")
//        TabBarButton(icon = "📊", text = "Histórico", selected = true)
//    }
//}

@Composable
fun ReportSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column (
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
                    .border(0.5.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
                    .padding(12.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Média mensal",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = "153 kg",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(800)
                )
                Text(
                    text = "CO₂ / mês",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            Column (
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
                    .border(0.5.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
                    .padding(12.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Economizado",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = "25 kg",
                    color = Color.Green,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(800)
                )
                Text(
                    text = "vs. 3 meses atrás",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun HistorySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .padding(16.dp)
    ) {
//        Text(
//            text = "Relatórios",
//            color = textColors,
//            fontSize = 12.sp
//        )
//

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column (
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
                    .border(0.5.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
                    .padding(12.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Março 2026 [Atual]",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = "142 kg CO₂",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(800)
                )
                Text(
                    text = "🚗 87kg      🥦55kg",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column (
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
                    .border(0.5.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
                    .padding(12.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Fevereiro 2026",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = "154 kg CO₂",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(800)
                )
                Text(
                    text = "🚗 95kg      🥦59kg",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column (
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
                    .border(0.5.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
                    .padding(12.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Janeiro 2026",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = "147 kg CO₂",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(800)
                )
                Text(
                    text = "🚗 87kg      🥦55kg",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

        }
    }
}