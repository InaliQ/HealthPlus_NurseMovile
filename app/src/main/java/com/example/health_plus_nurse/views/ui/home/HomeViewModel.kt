package com.example.health_plus_nurse.views.ui.home

import RetrofitClient.instancePacientes
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.health_plus_nurse.models.Paciente
import com.example.health_plus_nurse.models.Paciente2
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {


    private val _pacientes = MutableLiveData<List<Paciente2>?>()
    val pacientes: MutableLiveData<List<Paciente2>?> = _pacientes

    // Función para obtener pacientes
    fun obtenerPacientes(idEnfermero: Int) {
        instancePacientes.getPacientes(idEnfermero).enqueue(object : Callback<List<Paciente2>> {
            override fun onResponse(call: Call<List<Paciente2>>, response: Response<List<Paciente2>>) {
                if (response.isSuccessful) {
                    // Aquí, la respuesta es una lista de pacientes, no necesitamos usar Gson manualmente
                    val pacientes = response.body() // Obtener la lista de pacientes directamente
                    _pacientes.postValue(pacientes) // Establecer la lista de pacientes en el LiveData
                    Log.d("HomeViewModel", "Pacientes obtenidos: $pacientes")
                } else {
                    Log.e("HomeViewModel", "Error en la respuesta: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<List<Paciente2>>, t: Throwable) {
                Log.e("HomeViewModel", "Fallo en la solicitud: ${t.message}")

            }
        })
    }

}