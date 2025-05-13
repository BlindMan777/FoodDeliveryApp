package com.fooddeliveryappui.app.screens.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fooddeliveryappui.app.R
import com.fooddeliveryappui.app.components.AddToCartButton
import com.fooddeliveryappui.app.components.CustomCheckbox
import com.fooddeliveryappui.app.components.CustomDot
import com.fooddeliveryappui.app.components.DashedLine
import com.fooddeliveryappui.app.components.Devider
import com.fooddeliveryappui.app.components.FavouriteButton
import com.fooddeliveryappui.app.components.IncreaseButton
import com.fooddeliveryappui.app.components.Rate
import com.fooddeliveryappui.app.components.DecreaseButton
import com.fooddeliveryappui.app.screens.food.MenuTabsContentItems

@Composable
fun OrderScreen(
    viewModel: OrderScreenViewModel = hiltViewModel(),
    item: MenuTabsContentItems
) {
    val counterState = viewModel.counterState.collectAsStateWithLifecycle()
    val selectedIngredients = viewModel.selectedIngredients.collectAsStateWithLifecycle()

    HeaderOrder(
        item = item
    )
    Spacer(Modifier.height(12.dp))
    ContentOrder(
        item = item,
        counterState = counterState.value,
        onClickIncrease = { viewModel.іncreaseValue() },
        onClickDecrease = { viewModel.decreaseValue() },
        selectedIngredients = selectedIngredients.value,
        onCheckChange = viewModel::toggleIngredient
    )
}

@Composable
fun ContentOrder(
    modifier: Modifier = Modifier,
    item: MenuTabsContentItems,
    counterState: Int,
    selectedIngredients: Set<String>,
    onClickIncrease: () -> Unit,
    onClickDecrease: () -> Unit,
    onCheckChange: (String) -> Unit
) {
    val isEnabled = counterState > 1

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .padding(horizontal = 36.dp, vertical = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(320.dp),
            painter = painterResource(item.imgID),
            contentDescription = item.label,
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(36.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$${item.price}0",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 22.sp
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DecreaseButton(
                    isEnabled = isEnabled,
                    onClickDecrease = onClickDecrease
                )
                Text(
                    text = "$counterState",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 22.sp
                )
                IncreaseButton(
                    onClickIncrease = onClickIncrease
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Devider()
        Spacer(Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = item.descr,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.Light,
            lineHeight = 12.sp
        )
        Spacer(Modifier.height(36.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Add on ingredients",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.Bold,
            lineHeight = 18.sp
        )
        Spacer(Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IngredientsRow(
                text = "Shrimp",
                selected = selectedIngredients.contains("Shrimp"),
                price = 2.99,
                onCheckChange = { onCheckChange("Shrimp") }
            )
            IngredientsRow(
                text = "Crisp Onion",
                selected = selectedIngredients.contains("Crisp Onion"),
                price = 3.99,
                onCheckChange = { onCheckChange("Crisp Onion") }
            )
            IngredientsRow(
                text = "Sweet Corn",
                selected = selectedIngredients.contains("Sweet Corn"),
                price = 3.99,
                onCheckChange = { onCheckChange("Sweet Corn") }
            )
            IngredientsRow(
                text = "Pico de Gallo",
                selected = selectedIngredients.contains("Pico de Gallo"),
                price = 2.99,
                onCheckChange = { onCheckChange("Pico de Gallo") }
            )
        }
        Spacer(Modifier.height(28.dp))
        AddToCartButton()
    }
}

@Composable
fun HeaderOrder(
    modifier: Modifier = Modifier,
    item: MenuTabsContentItems
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(13.dp),
                        painter = painterResource(R.drawable.back_icon),
                        contentDescription = stringResource(R.string.turn_back),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = item.label,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Spacer(Modifier.width(4.dp))
                    CustomDot()
                }
                Row {
                    Spacer(Modifier.width(14.dp))
                    Rate(rate = item.rate)
                }
            }
            FavouriteButton()
        }
    }
}

@Composable
fun IngredientsRow(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    price: Double,
    onCheckChange: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1.3f),
            text = text,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            lineHeight = 14.sp
        )
        DashedLine(
            modifier = Modifier
                .weight(2f)
        )
        Spacer(Modifier.width(4.dp))
        Row(
            modifier = Modifier
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$$price",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                lineHeight = 14.sp
            )
            Spacer(Modifier.width(8.dp))
            CustomCheckbox(
                checked = selected,
                onCheckedChange = onCheckChange,
                size = 18.dp
            )
        }
    }
}
