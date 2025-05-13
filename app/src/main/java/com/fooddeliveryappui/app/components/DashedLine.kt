package com.fooddeliveryappui.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DashedLine(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 2.dp,
    dashWidth: Dp = 4.dp,
    dashGap: Dp = 4.dp,
    color: Color = MaterialTheme.colorScheme.surface
) {
    Box(
        modifier = modifier
            .height(1.dp)
            .drawBehind {
                val strokeWidth = strokeWidth.toPx()
                val dashWidth = dashWidth.toPx()
                val dashGap = dashGap.toPx()
                drawLine(
                    color = color,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = strokeWidth,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, dashGap))
                )
            }
    )
}


