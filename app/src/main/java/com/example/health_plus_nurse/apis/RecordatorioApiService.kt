package com.example.health_plus_nurse.apis

import com.example.health_plus_nurse.models.RecordatorioRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RecordatorioApiService {
    @GET("Recordatorio/UltimaAlertaRecordatorio/{idPaciente}")
    fun getRecordatorios(@Path("idPaciente") idPaciente: Int): Call<List<RecordatorioRequest>>

}