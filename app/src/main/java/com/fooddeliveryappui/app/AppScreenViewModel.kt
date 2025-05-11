package com.fooddeliveryappui.app

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject



@HiltViewModel
class AppScreenViewModel @Inject constructor() : ViewModel() {

    private val _selectedMenuItem = MutableStateFlow<MenuTabsList>(MenuTabsList.Dessert)
    val selectedMenuItem: StateFlow<MenuTabsList> = _selectedMenuItem

    private val _textState = MutableStateFlow("")
    val textState: StateFlow<String> = _textState

    fun updateText(newText: String) {
        _textState.value = newText
    }

    fun selectTabItem(item: MenuTabsList) {
        _selectedMenuItem.update { item }
    }

}