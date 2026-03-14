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
    type: FootprintRegisterType,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(120.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {
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
                text = "78"
            )
        }
    }
}