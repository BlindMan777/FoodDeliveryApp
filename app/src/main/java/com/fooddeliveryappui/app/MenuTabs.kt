package com.fooddeliveryappui.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class MenuTabsList(val label: Int, val iconID: Int, val horizontalPaddings: Dp) {
    data object Snacks : MenuTabsList(R.string.snacks, R.drawable.snacks_icon, 8.dp)
    data object Meal : MenuTabsList(R.string.meal, R.drawable.meal_icon, 16.dp)
    data object Vegan : MenuTabsList(R.string.vegan, R.drawable.vegan_icon, 6.dp)
    data object Dessert : MenuTabsList(R.string.dessert, R.drawable.dessert_icon, 10.dp)
    data object Drinks : MenuTabsList(R.string.drinks, R.drawable.drinks_icon, 14.dp)
}

data class MenuTabsContentItems(
    val label: String,
    val imgID: Int,
    val rate: Double,
    val price: Double,
    val descr: String
)

@Composable
fun MenuTabs(
    modifier: Modifier = Modifier
) {
    val menuItems = listOf(
        MenuTabsList.Snacks,
        MenuTabsList.Meal,
        MenuTabsList.Vegan,
        MenuTabsList.Dessert,
        MenuTabsList.Drinks
    )

    val menuDessertItems = listOf(
        MenuTabsContentItems(
            stringResource(R.string.chocolate_brownie),
            R.drawable.brownie_img,
            5.0,
            15.0,
            stringResource(R.string.chocolate_brownie_descr)
        ),
        MenuTabsContentItems(
            stringResource(R.string.macarons),
            R.drawable.macarons_img,
            4.0,
            12.9,
            stringResource(R.string.macarons)
        ),
        MenuTabsContentItems(
            stringResource(R.string.chocolate_brownie),
            R.drawable.brownie_img,
            5.0,
            15.0,
            stringResource(R.string.chocolate_brownie_descr)
        ),
        MenuTabsContentItems(
            stringResource(R.string.macarons),
            R.drawable.macarons_img,
            4.0,
            12.9,
            stringResource(R.string.macarons_descr)
        )
    )

    var selectedMenuItem: MenuTabsList by remember {
        mutableStateOf(MenuTabsList.Dessert)
    }

    val selectedIndex = menuItems.indexOf(selectedMenuItem)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(103.dp)
                    .width(26.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(topStart = 20.dp)
                    )
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = if (selectedIndex == 0) RoundedCornerShape(
                            bottomEnd = 30.dp,
                            topStart = 12.dp
                        ) else RoundedCornerShape(topStart = 12.dp)
                    )
            )
            menuItems.forEachIndexed { index, item ->

                val isSelected = selectedMenuItem == item
                val isNeighborLeft = index == selectedIndex - 1
                val isNeighborRight = index == selectedIndex + 1

                MenuTab(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                        )
                        .background(
                            color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                            shape =
                                if (isSelected) {
                                    RoundedCornerShape(30.dp)
                                } else if (isNeighborLeft) {
                                    RoundedCornerShape(bottomEnd = 30.dp)
                                } else if (isNeighborRight) {
                                    RoundedCornerShape(bottomStart = 30.dp)
                                } else {
                                    RoundedCornerShape(0.dp)
                                }
                        )
                        .clip(shape = RoundedCornerShape(30.dp)),
                    menuTab = item,
                    iconBackgroundColor = if (selectedMenuItem == item) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onSurface,
                    onClick = {
                        selectedMenuItem = item
                    }
                )
            }
            Box(
                modifier = Modifier
                    .height(103.dp)
                    .width(26.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(topEnd = 20.dp)
                    )
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = if (selectedIndex == menuItems.size - 1) RoundedCornerShape(
                            bottomStart = 30.dp,
                            topEnd = 12.dp
                        ) else RoundedCornerShape(topEnd = 12.dp)
                    )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = if (selectedIndex == 0) RoundedCornerShape(topEnd = 30.dp) else if (selectedIndex == menuItems.size - 1) RoundedCornerShape(
                        topStart = 30.dp
                    ) else RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(horizontal = 36.dp, 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        text = "Sort By",
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Popular",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                }
                FilterButton()
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(26.dp)
            ) {
                items(menuDessertItems) { item ->
                    MenuTabContentItem(
                        menuTabsContentItem = item
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.MenuTab(
    modifier: Modifier = Modifier,
    menuTab: MenuTabsList,
    iconColor: Color = MaterialTheme.colorScheme.primary,
    iconBackgroundColor: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(
                        color = iconBackgroundColor,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(horizontal = menuTab.horizontalPaddings, vertical = 12.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(37.dp),
                    painter = painterResource(menuTab.iconID),
                    contentDescription = stringResource(menuTab.label),
                    tint = iconColor
                )
            }
            Text(
                text = stringResource(menuTab.label),
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun MenuTabContentItem(
    modifier: Modifier = Modifier,
    menuTabsContentItem: MenuTabsContentItems
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .height(176.dp)
                .fillMaxWidth(),
            painter = painterResource(menuTabsContentItem.imgID),
            contentDescription = menuTabsContentItem.label
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = menuTabsContentItem.label,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.SemiBold
                )
                Box(
                    modifier = Modifier
                        .height(5.dp)
                        .width(5.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(100)
                        )
                )
                Row(
                    modifier = Modifier
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
                        text = "${menuTabsContentItem.rate}",
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
            Text(
                text = "$${menuTabsContentItem.price}0",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(3f),
                text = menuTabsContentItem.descr,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                fontWeight = FontWeight.Light,
                lineHeight = 10.sp
            )
            Spacer(modifier = Modifier.weight(2f))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(100)
                )
        )
    }
}