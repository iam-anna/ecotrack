package com.fiapos.ecotrack.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fiapos.ecotrack.controller.MainController
import com.fiapos.ecotrack.ui.EcotrackColor
import com.fiapos.ecotrack.ui.components.ArrowShapedButton
import com.fiapos.ecotrack.ui.components.CategoryProgressCard
import com.fiapos.ecotrack.ui.components.EcotrackLogo
import com.fiapos.ecotrack.ui.components.FootprintRegisterType


@Composable
fun HomeScreen(controller: MainController) {
    var progress = 0.35f

    Column (modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF0FDF4),
                            Color(0xFFDCFCE7)
                        )
                    )
                )
                .padding(
                    top = 48.dp,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 20.dp
                )
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Março 2026",
                        fontSize = 16.sp,
                        color = Color(0xFF14532D)
                    )

                    Text(
                        text = "Sua pegada de carbono",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF14532D)
                    )
                }

                EcotrackLogo()
            }

            Spacer(modifier = Modifier.height(12.dp))

            FootprintCard(0.4f)
        }

        Column(modifier = Modifier
            .padding(
                top = 12.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 42.dp
            ),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Breakdown por Categoria",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF374151)
                )

                Row (horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    CategoryProgressCard(
                        "87",
                        FootprintRegisterType.TRANSPORT,
                        0.7f,
                        modifier = Modifier.weight(1f)
                    )

                    CategoryProgressCard(
                        footprint = "55",
                        FootprintRegisterType.FOOD,
                        0.34f,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Calcular Emissões",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF374151)
                )

                Column (verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    ArrowShapedButton(
                        "🚗",
                        "Calcular Transporte",
                        "Carros, motos, ônibus e mais",
                        EcotrackColor.BLUE,
                        onClick = { controller.goToTransport() }
                    )

                    ArrowShapedButton(
                        "🥦",
                        "Calcular Alimentação",
                        "Carne, vegetais e industrializados",
                        EcotrackColor.GREEN,
                        onClick = { controller.goToHome() }
                    )

                    ArrowShapedButton(
                        "📊",
                        "Ver Histórico",
                        "Relatórios e progresso",
                        EcotrackColor.PURPLE,
                        onClick = { controller.goToHome() }
                    )
                }
            }

            Callout()
        }
    }
}

@Composable
fun FootprintCard(progress: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(
                vertical = 20.dp,
                horizontal = 20.dp
            )
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = "Total este mês",
                    fontSize = 16.sp,
                    color = Color(0xFF6B7280)
                )

                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "142",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF6B7280)
                    )

                    Text(
                        text = "kg CO₂",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF6B7280)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFFEF3C7),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "⚠\uFE0F Médio Impacto",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFD97706)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        CarbonProgressBar(progress)

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF0FDF4),
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = "\uD83D\uDCC9 Você emitiu 8% menos que no mês passado",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF16A34A)
            )
        }
    }
}

@Composable
fun CarbonProgressBar(
    progress: Float, // 0f..1f
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("0 kg", color = Color(0xFF9CA3AF))
            Text("500 kg", color = Color(0xFF9CA3AF))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(
                    color = Color(0xFFF1F5F9),
                    shape = RoundedCornerShape(50)
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF22C55E),
                                Color(0xFFCA8A04),
                                Color(0xFFD97706)
                            )
                        ),
                        shape = RoundedCornerShape(50)
                    )

            )
        }
    }
}

@Composable
fun Callout() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFEF3C7),
                        Color(0xFFFDE68A)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = Color(0x99FFFFFF),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text("💡", fontSize = 20.sp)
        }
        Text(
            text = "**Dica do dia:** Reduzir o consumo de carne vermelha 2x por semana pode diminuir até 15% da sua pegada de carbono alimentar.",
            fontSize = 12.sp,
            color = Color(0xFF92400E)
        )
    }
}