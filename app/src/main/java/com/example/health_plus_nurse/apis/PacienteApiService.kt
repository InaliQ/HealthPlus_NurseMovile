package com.example.health_plus_nurse.apis

import com.example.health_plus_nurse.models.Paciente
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PacienteApiService {
    @GET("Pacientes/ListarPacientesDisponibles/{idEnfermero}")
    fun getPacientes(@Path("idEnfermero") idEnfermero: Int): Call<List<Paciente>>
}
