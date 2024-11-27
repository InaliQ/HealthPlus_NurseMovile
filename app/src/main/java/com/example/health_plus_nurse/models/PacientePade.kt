package com.example.health_plus_nurse.models


data class PadecimientoP(
    val idPadecimiento: Int,
    val nombrePadecimiento: String
)

data class PadecimientosResponseP(
    val `$values`: List<PadecimientoP>
)
