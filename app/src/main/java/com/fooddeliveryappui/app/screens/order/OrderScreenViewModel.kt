package com.fooddeliveryappui.app.screens.order

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OrderScreenViewModel @Inject constructor() : ViewModel() {

    private val _counterState = MutableStateFlow(1)
    val counterState: StateFlow<Int> = _counterState

    fun іncreaseValue() {
        _counterState.update { it + 1 }
    }

    fun decreaseValue() {
        _counterState.update { it - 1 }
    }

    private val _selectedIngredients = MutableStateFlow<Set<String>>(emptySet())
    val selectedIngredients: StateFlow<Set<String>> = _selectedIngredients

    fun toggleIngredient(name: String) {
        _selectedIngredients.update { current ->
            if (current.contains(name)) current - name else current + name
        }
    }
}