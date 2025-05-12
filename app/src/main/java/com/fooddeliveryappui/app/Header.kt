package com.fooddeliveryappui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.StateFlow

sealed class SearchItems(
    val iconID: Int,
    val descrID: Int,
    val horizontalPadding: Dp,
    val verticalPadding: Dp
) {
    data object Basket : SearchItems(R.drawable.basket_icon, R.string.basket, 5.dp, 5.dp)
    data object Notifications : SearchItems(R.drawable.notification_icon, R.string.notification, 6.dp, 3.dp)
    data object Profile : SearchItems(R.drawable.profile_icon, R.string.profile, 7.dp, 4.dp)
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    textState: String,
    updateText: (String) -> Unit
) {

    val items = listOf(
        SearchItems.Basket,
        SearchItems.Notifications,
        SearchItems.Profile
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        BasicTextField(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(12.dp, 3.dp, 5.dp, 3.dp)
                .weight(1f),
            value = textState,
            onValueChange = updateText,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondary
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (textState.isEmpty()) {
                            Text(
                                text = "Search",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                        innerTextField()
                    }
                    FilterButton()
                }
            },
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSecondary)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach { item ->
                SearchItem(
                    searchItem = item
                )
            }
        }
    }
}

@Composable
fun SearchItem(
    modifier: Modifier = Modifier,
    searchItem: SearchItems,
    iconColor: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .padding(searchItem.horizontalPadding, searchItem.verticalPadding)
            .clickable(
                onClick = {}
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(searchItem.iconID),
            contentDescription = stringResource(searchItem.descrID),
            tint = iconColor
        )
    }
}
