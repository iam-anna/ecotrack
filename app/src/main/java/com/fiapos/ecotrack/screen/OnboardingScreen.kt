package com.fiapos.ecotrack.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fiapos.ecotrack.R
import com.fiapos.ecotrack.controller.MainController
import com.fiapos.ecotrack.ui.components.EcotrackLogo
import com.fiapos.ecotrack.ui.components.PersonalizedButton

@Composable
fun OnboardingScreen(controller: MainController) {

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFDCFCE7),
            Color(0xFFE0F2FE)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {

        BackgroundShapes()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 26.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(60.dp))

            Header()

            Spacer(modifier = Modifier.weight(0.45f))

            Image(
                painter = painterResource(R.drawable.terra),
                contentDescription = "Planeta",
                modifier = Modifier.size(330.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Entenda e reduza seu\nimpacto no planeta",
                fontSize = 26.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF14532D),
                lineHeight = 30.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Calcule sua pegada de carbono, acompanhe\nseu progresso e descubra como fazer a diferença.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF4B7C59),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(22.dp))

            PersonalizedButton(
                text = "Começar",
                onclick = { controller.startApp() }
            )

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun Header() {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        EcotrackLogo()

        Spacer(modifier = Modifier.width(10.dp))

        Column {

            Text(
                text = "ECOTRACK",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp,
                color = Color(0xFF15803D)
            )

            Box(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .width(45.dp)
                    .height(3.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF4ADE80),
                                Color(0xFF38BDF8)
                            )
                        )
                    )
            )
        }
    }
}

@Composable
fun BackgroundShapes() {

    Box(
        modifier = Modifier
            .size(200.dp)
            .offset(x = (-80).dp, y = (-40).dp)
            .background(Color(0xFFC2E6F8), CircleShape)
    )

    Box(
        modifier = Modifier
            .size(180.dp)
            .offset(x = 240.dp, y = (-30).dp)
            .background(Color(0xFFBCECD7), CircleShape)
    )

    Box(
        modifier = Modifier
            .size(240.dp)
            .offset(x = (-120).dp, y = 620.dp)
            .background(Color(0xFFB6E6D1), CircleShape)
    )
}