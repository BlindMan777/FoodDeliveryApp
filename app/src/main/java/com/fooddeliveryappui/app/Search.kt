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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fooddeliveryappui.app.ui.theme.Font
import com.fooddeliveryappui.app.ui.theme.OrangeBase
import com.fooddeliveryappui.app.ui.theme.White

sealed class SearchItems(val iconID: Int, val descrID: Int, val horizontalPadding: Int, val verticalPadding: Int) {
    data object Basket: SearchItems(R.drawable.basket_icon, R.string.basket, 5, 5)
    data object Notifications: SearchItems(R.drawable.notification_icon, R.string.notification, 6, 3)
    data object Profile: SearchItems(R.drawable.profile_icon, R.string.profile, 7, 4)
}

@Composable
fun Search(modifier: Modifier = Modifier) {
    var textState by remember {
        mutableStateOf("")
    }

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
                    color = White,
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(12.dp, 3.dp, 5.dp, 3.dp)
                .weight(1f),
            value = textState,
            onValueChange = { newText ->
                textState = newText
            },
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 12.sp,
                color = Font
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
            cursorBrush = SolidColor(Font)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach { item ->
                SearchItem(
                    modifier = Modifier
                        .background(
                            color = White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(item.horizontalPadding.dp, item.verticalPadding.dp),
                    iconID = item.iconID,
                    descriptionID = item.descrID
                )
            }
        }
    }
}

@Composable
fun SearchItem(
    modifier: Modifier = Modifier,
    iconID: Int,
    descriptionID: Int,
    iconColor: Color = OrangeBase
) {
    Row(
        modifier = modifier
            .clickable(
                onClick = {}
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(iconID),
            contentDescription = stringResource(descriptionID),
            tint = iconColor
        )
    }
}