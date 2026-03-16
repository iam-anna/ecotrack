package com.fiapos.ecotrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fiapos.ecotrack.ui.EcotrackColor

@Composable
fun Header(
    title: String,
    subtitle: String,
    emoji: String,
    action: () -> Unit,
    color: EcotrackColor
) {
    val titleColor: Color
    val subTitleColor: Color
    val linearColor1: Color
    val linearColor2: Color

    when(color) {
        EcotrackColor.BLUE -> {
            titleColor = Color(0xFF0C4A6E)
            subTitleColor = Color(0xFF0369A1)
            linearColor1 = Color(0xFFE0F2FE)
            linearColor2 = Color(0xFFBAE6FD)
        }
        EcotrackColor.GREEN -> {
            titleColor = Color(0xFF14532D)
            subTitleColor = Color(0xFF16A34A)
            linearColor1 = Color(0xFFDCFCE7)
            linearColor2 = Color(0xFFBBF7D0)
        }
        EcotrackColor.PURPLE -> {
            titleColor = Color(0xFF4C1D95)
            subTitleColor = Color(0xFF7C3AED)
            linearColor1 = Color(0xFFF3E8FF)
            linearColor2 = Color(0xFFDDD6FE)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        linearColor1,
                        linearColor2
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(300f, 300f) // cria o efeito diagonal ~135°
                ),
            )
            .padding(
                top = 48.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 20.dp
            )
    ){
        Button(
            modifier = Modifier.padding(all = 0.dp),
            onClick = action,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "‹ Voltar",
                color = subTitleColor,
                fontSize = 16.sp,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color(0x99FFFFFF),
                    shape = RoundedCornerShape(16.dp)
                ),
                contentAlignment = Alignment.Center
            ){
                Text(emoji, fontSize = 24.sp)
            }

            Column (
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = subtitle,
                    color = subTitleColor,
                    fontSize = 12.sp
                )

                Text(
                    text = title,
                    color = titleColor,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
            }
        }
    }
}