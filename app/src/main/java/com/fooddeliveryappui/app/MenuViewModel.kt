package com.fooddeliveryappui.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject



@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {

    private val _selectedMenuItem = MutableStateFlow<MenuTabsList>(MenuTabsList.Dessert)
    val selectedMenuItem: StateFlow<MenuTabsList> = _selectedMenuItem

    private val _textState = MutableStateFlow("")
    val textState: StateFlow<String> = _textState

    fun updateText(newText: String) {
        _textState.update { newText }
    }

    fun selectTabItem(item: MenuTabsList) {
        _selectedMenuItem.update { item }
    }

}
