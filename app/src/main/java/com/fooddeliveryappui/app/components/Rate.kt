package com.fooddeliveryappui.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fooddeliveryappui.app.R

@Composable
fun Rate(
    modifier: Modifier = Modifier,
    rate: Double
) {
    Row(
        modifier = modifier
            .height(16.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$rate",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.secondary,
            lineHeight = 12.sp
        )
        Icon(
            modifier = Modifier
                .size(12.dp),
            painter = painterResource(R.drawable.star_icon),
            contentDescription = stringResource(R.string.star),
            tint = MaterialTheme.colorScheme.background
        )
    }
}
