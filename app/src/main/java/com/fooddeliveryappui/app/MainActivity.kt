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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fooddeliveryappui.app.ui.theme.FoodDeliveryAppUITheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

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
    val textState = viewModel.textState.collectAsStateWithLifecycle()
    val view = LocalView.current
    LaunchedEffect(Unit) {
        delay(100)
        view.requestLayout()
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomNavBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(62.dp))
            Header(
                textState = textState.value,
                updateText = { newText ->
                    viewModel.updateText(newText)
                }
            )
            Spacer(modifier = Modifier.height(28.dp))
            MenuTabs()
        }
    }
}
