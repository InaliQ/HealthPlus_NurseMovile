package com.example.health_plus_nurse.apis

import com.example.health_plus_nurse.models.Paciente
import com.example.health_plus_nurse.models.Paciente2
import com.example.health_plus_nurse.models.PadecimientoP
import com.example.health_plus_nurse.models.PadecimientosResponseP
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PacienteApiService {
    @GET("Pacientes/ListarPacientesDisponibles/{idEnfermero}")
    fun getPacientes(@Path("idEnfermero") idEnfermero: Int): Call<List<Paciente2>>

    @POST("Pacientes/AgregarPaciente")
    fun agregarPaciente(@Body paciente: Paciente): Call<Void>

    @GET("Padecimientos/ListarPadecimientos")
    fun getPadecimientos(): Call<PadecimientosResponseP>
}

