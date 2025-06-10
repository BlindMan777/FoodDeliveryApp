package com.fooddeliveryappui.app.components

import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fooddeliveryappui.app.R

@Composable
fun CustomIconButton(
    modifier: Modifier = Modifier,
    btnSize: Dp = 22.dp,
    iconID: Int,
    iconSize: Dp = 12.dp,
    iconDescr: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .size(btnSize)
            .clip(RoundedCornerShape(100)),
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = MaterialTheme.colorScheme.secondary
        ),
        enabled = isEnabled
    ) {
        Icon(
            modifier = Modifier
                .size(iconSize),
            painter = painterResource(iconID),
            contentDescription = iconDescr
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
