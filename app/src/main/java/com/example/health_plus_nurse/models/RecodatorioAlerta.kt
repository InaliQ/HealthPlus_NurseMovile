package com.example.health_plus_nurse.models


data class UltimaAlerta(
    val idAlerta: Int,
    val fechaHora: String,
    val descripcion: String,
    val idPaciente: Int
)

data class UltimoRecordatorio(
    val idRecordatorio: Int,
    val medicamento: String,
    val cantidadMedicamento: String,
    val fechaInicio: String,
    val fechaFin: String,
    val estatus: Boolean,
    val idPaciente: Int
)

data class RecordatorioResponse(
    val ultimaAlerta: UltimaAlerta?,
    val ultimoRecordatorio: UltimoRecordatorio?
)

