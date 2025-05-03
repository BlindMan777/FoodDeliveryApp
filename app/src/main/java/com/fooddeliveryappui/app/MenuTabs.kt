package com.fooddeliveryappui.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fooddeliveryappui.app.ui.theme.Font
import com.fooddeliveryappui.app.ui.theme.OrangeBase
import com.fooddeliveryappui.app.ui.theme.White
import com.fooddeliveryappui.app.ui.theme.YellowBase
import com.fooddeliveryappui.app.ui.theme.Yellow_2

sealed class MenuTabsList(val label: Int, val iconID: Int, val horizontalPaddings: Int) {
    data object Snacks: MenuTabsList(R.string.snacks, R.drawable.snacks_icon, 8)
    data object Meal: MenuTabsList(R.string.meal, R.drawable.meal_icon, 16)
    data object Vegan: MenuTabsList(R.string.vegan, R.drawable.vegan_icon, 6)
    data object Dessert: MenuTabsList(R.string.dessert, R.drawable.dessert_icon, 10)
    data object Drinks: MenuTabsList(R.string.drinks, R.drawable.drinks_icon, 14)
}

data class MenuTabsContentItems(val label: Int, val imgID: Int, val rate: Double, val price: Double, val descr: Int)

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
        MenuTabsContentItems(R.string.chocolate_brownie, R.drawable.brownie_img, 5.0, 15.0, R.string.chocolate_brownie_descr),
        MenuTabsContentItems(R.string.macarons, R.drawable.macarons_img, 4.0, 12.9, R.string.macarons_descr)
        )

    var selectedMenuItem: MenuTabsList by remember {
        mutableStateOf(MenuTabsList.Dessert)
    }

    val selectedIndex = menuItems.indexOf(selectedMenuItem)

    Column (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = OrangeBase,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .height(103.dp)
                    .width(26.dp)
                    .background(
                        color = White,
                        shape = RoundedCornerShape(topStart = 20.dp)
                    )
                    .background(
                        color = OrangeBase,
                        shape = if(selectedIndex == 0) RoundedCornerShape(bottomEnd = 30.dp, topStart = 12.dp) else RoundedCornerShape(topStart = 12.dp)
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
                            color = White,
                            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                        )
                        .background(
                            color = if(isSelected) White else OrangeBase,
                            shape =
                            if(isSelected) {
                                RoundedCornerShape(30.dp)
                            }else if(isNeighborLeft){
                                RoundedCornerShape(bottomEnd = 30.dp)
                            }else if(isNeighborRight){
                                RoundedCornerShape(bottomStart = 30.dp)
                            } else {
                                RoundedCornerShape(0.dp)
                            }
                        )
                        .clip(shape = RoundedCornerShape(30.dp)),
                    iconID = item.iconID,
                    label = item.label,
                    horizontalPaddings = item.horizontalPaddings,
                    iconBackgroundColor = if (selectedMenuItem == item) YellowBase else Yellow_2,
                    onClick = {
                        selectedMenuItem = item
                    }
                )
            }
            Spacer(
                modifier = Modifier
                    .height(103.dp)
                    .width(26.dp)
                    .background(
                        color = White,
                        shape = RoundedCornerShape(topEnd = 20.dp)
                    )
                    .background(
                        color = OrangeBase,
                        shape = if(selectedIndex == menuItems.size - 1) RoundedCornerShape(bottomStart = 30.dp, topEnd = 12.dp) else RoundedCornerShape(topEnd = 12.dp)
                    )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    color = White,
                    shape = if(selectedIndex == 0) RoundedCornerShape(topEnd = 30.dp) else if(selectedIndex == menuItems.size - 1) RoundedCornerShape(topStart = 30.dp) else RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(horizontal = 36.dp, 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        text = "Sort By",
                        color = Color(0xFF070707),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Popular",
                        color = Color(0xFFE95322),
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
               for(i in 0 .. 2) {
                   items(menuDessertItems) { item ->
                       MenuTabContentItem(
                           imgID = item.imgID,
                           label = item.label,
                           rate = item.rate,
                           price = item.price,
                           descr = item.descr
                       )
                   }
               }
            }
        }
    }
}

@Composable
fun RowScope.MenuTab(
    modifier: Modifier = Modifier,
    iconID: Int,
    label: Int,
    iconColor: Color = OrangeBase,
    horizontalPaddings: Int,
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
                    .padding(horizontal = horizontalPaddings.dp, vertical = 12.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(37.dp),
                    painter = painterResource(iconID),
                    contentDescription = stringResource(label),
                    tint = iconColor
                )
            }
            Text(
                text = stringResource(label),
                color = Font,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun MenuTabContentItem(
    modifier: Modifier = Modifier,
    imgID: Int,
    label: Int,
    rate: Double,
    price: Double,
    descr: Int
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
            painter = painterResource(imgID),
            contentDescription = stringResource(label)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                Text(
                    text = stringResource(label),
                    fontSize = 18.sp,
                    color = Font,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(
                    modifier = Modifier
                        .height(5.dp)
                        .width(5.dp)
                        .background(
                            color = OrangeBase,
                            shape = RoundedCornerShape(100)
                        )
                )
                Row(
                    modifier = Modifier
                        .height(16.dp)
                        .background(
                            color = OrangeBase,
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
                        color = White,
                        lineHeight = 12.sp
                    )
                    Icon(
                        modifier = Modifier
                            .size(12.dp),
                        painter = painterResource(R.drawable.star_icon),
                        contentDescription = stringResource(R.string.star),
                        tint = YellowBase
                    )
                }
            }
            Text(
                text = "$${price}0",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = OrangeBase
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(3f),
                text = stringResource(descr),
                fontSize = 12.sp,
                color = Font,
                fontWeight = FontWeight.Light,
                lineHeight = 10.sp
            )
            Spacer(modifier = Modifier.weight(2f))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(
                    color = OrangeBase,
                    shape = RoundedCornerShape(100)
                )
        )
    }
}