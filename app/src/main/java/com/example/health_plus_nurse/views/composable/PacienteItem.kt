// PacienteList.kt
package com.example.health_plus_nurse.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.health_plus_nurse.models.Paciente

// Composable para un solo paciente
@Composable
fun PacienteItem(paciente: Paciente, onButtonClick1: (Paciente) -> Unit, onButtonClick2: (Paciente) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${paciente.nombre} ${paciente.primerApellido} ${paciente.segundoApellido}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Teléfono: ${paciente.telefono}", style = MaterialTheme.typography.bodySmall)
            Text(
                text = "Fecha de nacimiento: ${paciente.fechaNacimiento}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { onButtonClick1(paciente) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Botón 1")
                }
                Button(
                    onClick = { onButtonClick2(paciente) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Botón 2")
                }
            }
        }
    }
}

// Composable para la lista de pacientes
@Composable
fun PacientesList(pacientes: List<Paciente>, onButtonClick1: (Paciente) -> Unit, onButtonClick2: (Paciente) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(pacientes) { paciente ->
            PacienteItem(paciente = paciente, onButtonClick1 = onButtonClick1, onButtonClick2 = onButtonClick2)
        }
    }
}
