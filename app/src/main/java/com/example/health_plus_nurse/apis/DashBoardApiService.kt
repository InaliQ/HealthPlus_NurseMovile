package com.example.health_plus_nurse.apis

import com.example.health_plus_nurse.models.DashBoardRequest
import com.example.health_plus_nurse.models.Padecimiento
import com.example.health_plus_nurse.models.PadecimientoResponse
import com.example.health_plus_nurse.models.PadecimientoXpersona
import com.example.health_plus_nurse.models.PadecimientoXpersonaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DashBoardApiService {

    @GET("Dashboard/DashboardRitmo/{idPaciente}/{fecha}")
    fun getDashboardData(
        @Path("idPaciente") idPaciente: Int,
        @Path("fecha") fecha: String
    ): Call<DashBoardRequest.RitmoResponse>

    @GET("Dashboard/PacientesXPadecimiento")
    fun getPacientesXPadecimiento(): Call<List<PadecimientoResponse>>

    @GET("Dashboard/PacientesPorEdad")
    fun getPadecimientosXEdad(): Call<PadecimientoXpersonaResponse>
}