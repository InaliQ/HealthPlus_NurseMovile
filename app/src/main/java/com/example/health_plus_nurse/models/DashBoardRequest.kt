package com.example.health_plus_nurse.models

class DashBoardRequest {

    data class RitmoResponse(
        val values: List<Ritmo>
    )

    data class Ritmo(
        val idRitmo: Int,
        val max: Int,
        val min: Int,
        val medicion: Int,
        val idPadecimiento: Int,
        val fechaRegistro: String
    )

}