package com.upchiapas.kt_template.Calculadora.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CascadaPage(viewModel: CalculadoraNeumaticaViewModel = viewModel()) {

    val mensaje by viewModel.mensaje.collectAsStateWithLifecycle()
    val entrada by viewModel.entradaTexto.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Calculadora Cascada") })
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = mensaje,
                fontSize = 22.sp,
                color = Color.Blue
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = entrada,
                onValueChange = { viewModel.entradaDeTexto(it) },
                label = { Text("Secuencia Neumática") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { viewModel.evaluarSecuencia() },
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text("Evaluar")
            }

        }
    }
}