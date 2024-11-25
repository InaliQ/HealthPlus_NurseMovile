package com.example.health_plus_nurse.models

data class RecordatorioRequest(
    val medicamento: String,
    val cantidadMedicamento: String,
    val fechaInicio: String, // Formato ISO 8601 (ejemplo: 2024-11-19T03:06:08.620Z)
    val fechaFin: String,    // Formato ISO 8601
    val estatus: Boolean,
    val idEnfermero: Int
)
