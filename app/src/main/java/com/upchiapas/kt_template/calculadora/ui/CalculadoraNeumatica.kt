package com.upchiapas.kt_template.calculadora.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.upchiapas.kt_template.R
import com.upchiapas.kt_template.calculadora.viewmodel.CalculadoraNeumaticaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CascadaPage(viewModel: CalculadoraNeumaticaViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.titulo_pantalla)) }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ResultadoCard(mensaje = uiState.mensaje, esValida = uiState.esValida)
            EntradaSecuencia(
                valor = uiState.entradaTexto,
                onValueChange = viewModel::entradaDeTexto
            )
            ChipsEjemplos(onSeleccionar = viewModel::entradaDeTexto)
            BotonEvaluar(onClick = viewModel::evaluarSecuencia)
            HistorialLista(historial = uiState.historial)
        }
    }
}
