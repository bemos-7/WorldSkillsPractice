package com.example.viewpagertesting

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun SlideAndTiltScreenExample() {
    var isScreenShifted by remember { mutableStateOf(false) }

    // Получение плотности экрана через LocalDensity
    val density = LocalDensity.current.density

    // Анимации для смещения и наклона
    val offsetX by animateDpAsState(
        targetValue = if (isScreenShifted) 250.dp else 0.dp, // Смещение вправо
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 500)
    )
    val rotationZ by animateFloatAsState(
        targetValue = if (isScreenShifted) -6f else 0f, // Поворот в градусах
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 500)
    )
    val scale by animateFloatAsState(
        targetValue = if (isScreenShifted) 0.8f else 1f, // Масштабирование экрана
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 500)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Боковое меню
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2CBAF1)) // Синий фон
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
        }

        // Основной экран с наклоном
        Box(
            modifier = Modifier
                .offset(x = offsetX) // Смещение вправо
                .graphicsLayer(
                    rotationZ = rotationZ,
                    cameraDistance = 8 * density,
                    scaleX = scale,
                    scaleY = scale,
                )
                .fillMaxHeight()
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(topStart = 35.dp, bottomStart = 35.dp)
                )
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Основной экран", color = Color.Black)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { isScreenShifted = !isScreenShifted }) {
                    Text(if (isScreenShifted) "Вернуть экран" else "Открыть меню")
                }
            }
        }
    }
}