package com.upchiapas.kt_template.calculadora.viewmodel

import androidx.lifecycle.ViewModel
import com.upchiapas.kt_template.calculadora.model.CalculadoraUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalculadoraNeumaticaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CalculadoraUiState())
    val uiState: StateFlow<CalculadoraUiState> = _uiState.asStateFlow()

    fun entradaDeTexto(nuevoTexto: String) {
        _uiState.update { it.copy(entradaTexto = nuevoTexto.uppercase()) }
    }

    fun evaluarSecuencia() {
        val entrada = _uiState.value.entradaTexto.trim()
        val resultado = validarSecuencia(entrada)
        _uiState.update { actual ->
            actual.copy(
                mensaje = resultado.first,
                esValida = resultado.second,
                historial = if (resultado.second) actual.historial + entrada else actual.historial
            )
        }
    }

    private fun validarSecuencia(entrada: String): Pair<String, Boolean> {
        if (entrada.length % 2 != 0 || entrada.length < 4)
            return "Secuencia inválida" to false

        val permitidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val simbolos = "+-"
        val estado = mutableMapOf<Char, Char>()
        val balance = mutableMapOf<Char, Int>()

        for (i in entrada.indices step 2) {
            val letra = entrada[i]
            val signo = entrada[i + 1]

            if (letra !in permitidos || signo !in simbolos)
                return "Secuencia inválida" to false

            if (estado[letra] == signo)
                return "Secuencia inválida" to false
            estado[letra] = signo

            balance[letra] = (balance[letra] ?: 0) + if (signo == '+') 1 else -1
        }

        if (balance.values.any { it != 0 }) return "Secuencia inválida" to false

        return "Secuencia válida" to true
    }
}
