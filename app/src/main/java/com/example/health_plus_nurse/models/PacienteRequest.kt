package com.example.health_plus_nurse.models

data class PacienteRequest(

    val nombre: String,
    val primerApellido: String,
    val segundoApellido: String,
    val telefono: String,
    val fechaNacimiento: String,
    val calle: String,
    val numero: String,
    val codigoPostal: String,
    val colonia: String,
    val numPaciente: String,
    val altura: String,
    val peso: String,
    val tipoSangre: String,
    val estatus: Boolean,
    val usuario1: String,
    val contrasenia: String,
    val idPaciente: Int = 0,
    val idPadecimiento: Int = 0,
    val max: Int = 0,
    val min: Int = 0,
    val idPadecimientoRitmo: Int = 0,
    val idPadecimientos: List<Int> = emptyList()
)

