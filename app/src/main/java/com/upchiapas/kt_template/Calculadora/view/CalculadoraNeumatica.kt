package com.upchiapas.kt_template.Calculadora.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CascadaPage(viewModel: CalculadoraNeumaticaViewModel = viewModel()) {

    val entrada by viewModel.entradaTexto.collectAsStateWithLifecycle()
    val mensaje by viewModel.mensaje.collectAsStateWithLifecycle()
    val esValida by viewModel.esValida.collectAsStateWithLifecycle()
    val historial by viewModel.historial.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Calculadora Cascada") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ResultadoCard(mensaje = mensaje, esValida = esValida)
            EntradaSecuencia(
                valor = entrada,
                onValueChange = viewModel::entradaDeTexto
            )
            ChipsEjemplos(onSeleccionar = viewModel::entradaDeTexto)
            BotonEvaluar(onClick = viewModel::evaluarSecuencia)
            HistorialLista(historial = historial)
        }
    }
}