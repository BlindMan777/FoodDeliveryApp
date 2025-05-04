package com.fooddeliveryappui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

sealed class BottomNavigationBarItems(val iconID: Int, val iconDescr: Int) {
    data object Home : BottomNavigationBarItems(R.drawable.home_icon, R.string.home)
    data object Plate : BottomNavigationBarItems(R.drawable.plate_icon, R.string.plate)
    data object Heart : BottomNavigationBarItems(R.drawable.heart_icon, R.string.heart)
    data object Menu : BottomNavigationBarItems(R.drawable.menu_icon, R.string.menu)
    data object Headphones :
        BottomNavigationBarItems(R.drawable.headphones_icon, R.string.headphones)
}

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavigationBarItems.Home,
        BottomNavigationBarItems.Plate,
        BottomNavigationBarItems.Heart,
        BottomNavigationBarItems.Menu,
        BottomNavigationBarItems.Headphones
    )
    NavigationBar(
        modifier = modifier
            .height(60.dp)
            .background(MaterialTheme.colorScheme.secondary)
            .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            items.forEach { item ->
                CustomNavBarItem(
                    modifier = Modifier
                        .weight(if (item == BottomNavigationBarItems.Plate) 1.2f else 1f),
                    navBarItem = item
                )
            }
        }
    }
}

@Composable
fun RowScope.CustomNavBarItem(
    modifier: Modifier = Modifier,
    navBarItem: BottomNavigationBarItems,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(
                onClick = {},
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(28.dp),
            painter = painterResource(navBarItem.iconID),
            contentDescription = stringResource(navBarItem.iconDescr)
        )
    }
}