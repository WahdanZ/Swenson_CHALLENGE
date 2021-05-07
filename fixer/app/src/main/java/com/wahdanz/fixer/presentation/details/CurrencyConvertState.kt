package com.wahdanz.fixer.presentation.details

sealed class CurrencyConvertState {
    object Loading : CurrencyConvertState()
    data class Update(val data: Double) : CurrencyConvertState()
    data class Error(val errorMsg: String) : CurrencyConvertState()
}