package com.upchiapas.kt_template.Calculadora.view

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculadoraNeumaticaViewModel : ViewModel() {

    private val _entradaTexto = MutableStateFlow("")
    val entradaTexto: StateFlow<String> = _entradaTexto.asStateFlow()

    private val _mensaje = MutableStateFlow("")
    val mensaje: StateFlow<String> = _mensaje.asStateFlow()

    private val _esValida = MutableStateFlow<Boolean?>(null)
    val esValida: StateFlow<Boolean?> = _esValida.asStateFlow()

    private val _historial = MutableStateFlow<List<String>>(emptyList())
    val historial: StateFlow<List<String>> = _historial.asStateFlow()

    fun entradaDeTexto(nuevoTexto: String) {
        _entradaTexto.value = nuevoTexto.uppercase()
    }

    fun evaluarSecuencia() {
        val entrada = _entradaTexto.value.trim()
        val resultado = validarSecuencia(entrada)
        _mensaje.value = resultado.first
        _esValida.value = resultado.second
        if (resultado.second) {
            _historial.value = _historial.value + entrada
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