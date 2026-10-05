package com.upchiapas.kt_template.Calculadora.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculadoraNeumaticaViewModel : ViewModel(){

    private val _entradaTexto = MutableStateFlow("")
    val entradaTexto: StateFlow<String> = _entradaTexto.asStateFlow()

    private val _mensaje = MutableStateFlow("")
    val mensaje: StateFlow<String> = _mensaje.asStateFlow()

    fun entradaDeTexto(nuevoTexto: String) {
        _entradaTexto.value = nuevoTexto.uppercase()
    }

    fun evaluarSecuencia() {
        val entrada = _entradaTexto.value.trim()

        if (entrada.length % 2 != 0 || entrada.length < 4) {
            _mensaje.value = "Secuencia inválida"
            return
        }

        val permitidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val simbolosP = "+-"
        val estadoCilindro = mutableMapOf<Char, Char>()
        val balanceCilindro = mutableMapOf<Char, Int>()

        for (i in entrada.indices step 2) {
            val letra = entrada[i]
            val signo = entrada[i + 1]

            if (!permitidos.contains(letra) || !simbolosP.contains(signo)) {
                _mensaje.value = "Secuencia inválida"
                return
            }

            if (estadoCilindro[letra] == signo) {
                _mensaje.value = "Secuencia inválida"
                return
            }
            estadoCilindro[letra] = signo

            val balanceActual = balanceCilindro.getOrDefault(letra, 0)
            if (signo == '+') balanceCilindro[letra] = balanceActual + 1
            else balanceCilindro[letra] = balanceActual - 1
        }

        for ((_, balance) in balanceCilindro) {
            if (balance != 0) {
                _mensaje.value = "Secuencia inválida"
                return
            }
        }

        _mensaje.value = "Secuencia válida"
    }

}