package com.upchiapas.kt_template.calculadora.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.upchiapas.kt_template.R

@Composable
fun ResultadoCard(mensaje: String, esValida: Boolean?) {
    if (mensaje.isBlank()) return
    val color = when (esValida) {
        true -> MaterialTheme.colorScheme.primary
        false -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.onSurface
    }
    Card(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = mensaje,
            color = color,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun EntradaSecuencia(valor: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.label_secuencia)) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun BotonEvaluar(onClick: () -> Unit) {
    Button(onClick = onClick) { 
        Text(stringResource(R.string.btn_evaluar)) 
    }
}

@Composable
fun ChipsEjemplos(onSeleccionar: (String) -> Unit) {
    val ejemplos = listOf("A+B+A-B-", "A+B-A-B+", "A+A-B+B-")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ejemplos.forEach { ej ->
            FilterChip(
                selected = false,
                onClick = { onSeleccionar(ej) },
                label = { Text(ej) }
            )
        }
    }
}

@Composable
fun HistorialLista(historial: List<String>) {
    if (historial.isEmpty()) return
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(historial) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(text = item, modifier = Modifier.padding(12.dp))
            }
        }
    }
}
