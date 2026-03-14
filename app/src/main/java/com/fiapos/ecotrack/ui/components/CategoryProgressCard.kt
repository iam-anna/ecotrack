package com.fiapos.ecotrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

enum class FootprintRegisterType {
    FOOD,
    TRANSPORT
}

@Composable
fun CategoryProgressCard(
    footprint: String,
    type: FootprintRegisterType,
    progress: Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = when(type) {
                FootprintRegisterType.FOOD -> "\uD83E\uDD66"
                FootprintRegisterType.TRANSPORT -> "\uD83D\uDE97"
            },
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = when(type) {
                FootprintRegisterType.FOOD -> "Alimentação"
                FootprintRegisterType.TRANSPORT -> "Transporte"
            },
            fontSize = 12.sp,
            color = Color(0xFF6B7280)
        )

        Row {
            Text(
                text = footprint,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF111827)
            )

            Text(
                text = "kg",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF9CA3AF)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
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
                        color = when(type) {
                            FootprintRegisterType.FOOD -> Color(0xFF388DF8)
                            FootprintRegisterType.TRANSPORT -> Color(0xFF4ADE80)
                        },
                        shape = RoundedCornerShape(50)
                    )

            )
        }
    }
}