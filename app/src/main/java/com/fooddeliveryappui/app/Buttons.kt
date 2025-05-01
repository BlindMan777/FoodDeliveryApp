package com.fooddeliveryappui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fooddeliveryappui.app.ui.theme.OrangeBase

@Composable
fun FilterButton(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = OrangeBase,
                shape = RoundedCornerShape(100)
            )
            .clickable(
                onClick = {}
            )
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(16.dp),
            painter = painterResource(R.drawable.filter_icon),
            contentDescription = stringResource(R.string.filter)
        )
    }
}