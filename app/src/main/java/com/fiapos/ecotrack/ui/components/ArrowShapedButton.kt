package com.fiapos.ecotrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class ArrowShapedButtonColor {
    BLUE,
    GREEN,
    PURPLE
}

@Composable
fun ArrowShapedButton(
    emoji: String,
    title: String,
    subtitle: String,
    color: ArrowShapedButtonColor
) {
    val titleColor: Color
    val subTitleColor: Color
    val linearColor1: Color
    val linearColor2: Color

    when(color) {
        ArrowShapedButtonColor.BLUE -> {
            titleColor = Color(0xFF0C4A6E)
            subTitleColor = Color(0xFF0369A1)
            linearColor1 = Color(0xFFE0F2FE)
            linearColor2 = Color(0xFFBAE6FD)
        }
        ArrowShapedButtonColor.GREEN -> {
            titleColor = Color(0xFF14532D)
            subTitleColor = Color(0xFF16A34A)
            linearColor1 = Color(0xFFDCFCE7)
            linearColor2 = Color(0xFFBBF7D0)
        }
        ArrowShapedButtonColor.PURPLE -> {
            titleColor = Color(0xFF4C1D95)
            subTitleColor = Color(0xFF7C3AED)
            linearColor1 = Color(0xFFF3E8FF)
            linearColor2 = Color(0xFFDDD6FE)
        }
    }

    Row(
        modifier = Modifier
        .fillMaxWidth()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    linearColor1,
                    linearColor2
                )
            ),
            shape = RoundedCornerShape(16.dp)
        )
        .padding(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(modifier = Modifier
            .size(48.dp)
            .background(
                color = Color(0x99FFFFFF),
                shape = RoundedCornerShape(16.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(emoji, fontSize = 24.sp)
        }

        Column (verticalArrangement = Arrangement.spacedBy(0.dp)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = titleColor
            )

            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = subTitleColor
            )
        }
    }
}