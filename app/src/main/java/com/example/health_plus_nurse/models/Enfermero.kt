package com.example.health_plus_nurse.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val NombreUsuario: String,
    val contrasenia: String
)

data class EnfermeroModel(
    @SerializedName("idEnfermero") val idEnfermero: Int,
)