package com.example.health_plus_nurse.models



data class PadecimientosWrapper(
    val `$values`: List<Padecimiento>
)

data class Padecimiento(
    val idPadecimiento: Int,
    val nombrePadecimiento: String
)

data class Paciente(
    val idPaciente: Int,
    val nombre: String,
    val primerApellido: String,
    val segundoApellido: String,
    val telefono: String,
    val fechaNacimiento: String,
    val calle: String,
    val numero: String,
    val colonia: String,
    val codigoPostal: String,
    val numPaciente: String,
    val altura: String,
    val peso: String,
    val tipoSangre: String,
    val estatus: Boolean,
    val usuario1: String,
    val contrasenia: String,
    val max: Int,
    val min: Int,
    val padecimientos: List<PadecimientoP>, // Cambiado de List<Padecimiento> a PadecimientosWrapper
    val idPadecimientos: List<Int>
)

data class Paciente2(
    val idPaciente: Int,
    val nombre: String,
    val primerApellido: String,
    val segundoApellido: String,
    val telefono: String,
    val fechaNacimiento: String,
    val calle: String,
    val numero: String,
    val colonia: String,
    val codigoPostal: String,
    val numPaciente: String,
    val altura: String,
    val peso: String,
    val tipoSangre: String,
    val estatus: Boolean,
    val usuario1: String,
    val contrasenia: String,
    val max: Int,
    val min: Int,
    val padecimientos: Padecimiento // Cambiado de List<Padecimiento> a PadecimientosWrapper
)

