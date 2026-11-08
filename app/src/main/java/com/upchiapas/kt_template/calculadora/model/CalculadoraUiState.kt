package com.upchiapas.kt_template.calculadora.model

data class CalculadoraUiState(
    val entradaTexto: String = "",
    val mensaje: String = "",
    val esValida: Boolean? = null,
    val historial: List<String> = emptyList()
)
