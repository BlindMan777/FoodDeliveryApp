package com.fooddeliveryappui.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomCheckbox(
    checked: Boolean,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    size: Dp
) {

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = color,
                shape = CircleShape
            )
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(size * 2 / 3)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = color,
                    shape = CircleShape
                )
                .background(
                    color = if (checked) color else MaterialTheme.colorScheme.secondary
                )
                .clickable(
                    onClick = onCheckedChange
                )
        )
    }
}