package com.fooddeliveryappui.app.components

import android.R.attr.enabled
import androidx.compose.material3.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fooddeliveryappui.app.R

@Composable
fun FilterButton(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(100)
            )
            .clip(RoundedCornerShape(100))
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
            contentDescription = stringResource(R.string.filter),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun FavouriteButton(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(100)
            )
            .clip(RoundedCornerShape(100))
            .clickable(
                onClick = {}
            )
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(16.dp),
            painter = painterResource(R.drawable.favourite_icon),
            contentDescription = stringResource(R.string.favourite),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun IncreaseButton(
    modifier: Modifier = Modifier,
    onClickIncrease: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(100)
            )
            .clip(RoundedCornerShape(100))
            .clickable(
                onClick = onClickIncrease
            )
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(12.dp),
            painter = painterResource(R.drawable.add_icon),
            contentDescription = stringResource(R.string.add_item),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun DecreaseButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    onClickDecrease: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = if (isEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(100)
            )
            .clip(RoundedCornerShape(100))
            .clickable(
                enabled = isEnabled,
                onClick = onClickDecrease
            )
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(12.dp),
            painter = painterResource(R.drawable.subtract_icon),
            contentDescription = stringResource(R.string.add_item),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun AddToCartButton(
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 4.dp),
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp),
            painter = painterResource(R.drawable.basket_icon),
            contentDescription = stringResource(R.string.basket)
        )
        Spacer(Modifier.width(14.dp))
        Text(
            text = "Add to Cart",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 18.sp
        )
    }
}
