package com.fiapos.ecotrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EcotrackLogo() {
    Box (modifier = Modifier
        .width(40.dp)
        .height(40.dp)
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF22C55E),
                    Color(0xFF059669)
                ),
                start = Offset(0f, 0f),
                end = Offset(300f, 300f) // cria o efeito diagonal ~135°
            ),
            shape = RoundedCornerShape(12.dp)
        ),
        contentAlignment = Alignment.Center
    ) {
        Text("\uD83C\uDF3F", fontSize = 18.sp)
    }
}