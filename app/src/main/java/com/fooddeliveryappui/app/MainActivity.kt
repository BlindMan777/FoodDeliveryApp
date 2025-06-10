package com.fooddeliveryappui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fooddeliveryappui.app.components.BottomNavBar
import com.fooddeliveryappui.app.screens.food.HeaderFood
import com.fooddeliveryappui.app.screens.food.MenuTabs
import com.fooddeliveryappui.app.screens.food.MenuViewModel
import com.fooddeliveryappui.app.screens.food.menuItems
import com.fooddeliveryappui.app.screens.order.OrderScreen
import com.fooddeliveryappui.app.ui.theme.FoodDeliveryAppUITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryAppUITheme {
                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen(
    viewModel: MenuViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val textState = viewModel.textState.collectAsStateWithLifecycle()

    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                BottomNavBar()
            }
        ) { innerPadding ->
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                navController = navController,
                startDestination = MenuRoute
            ) {
                composable(MenuRoute) {
                    Column() {
                        Spacer(modifier = Modifier.height(32.dp))
                        HeaderFood(
                            textState = textState.value,
                            updateText = { newText ->
                                viewModel.updateText(newText)
                            }
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                        MenuTabs()
                    }
                }
                composable(OrderRoute) {
                    OrderScreen(
                        item = menuItems.first()
                    )
                }
            }
        }
    }
}


