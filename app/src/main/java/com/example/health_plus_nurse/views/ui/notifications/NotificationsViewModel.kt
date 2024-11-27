package com.example.health_plus_nurse.views.ui.notifications

import androidx.lifecycle.ViewModel
import RetrofitClient.instancePacientes
import android.util.Log
import com.example.health_plus_nurse.models.Paciente
import com.example.health_plus_nurse.models.PadecimientoP
import com.example.health_plus_nurse.models.PadecimientosResponseP
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsViewModel : ViewModel() {


    fun agregarPaciente(paciente: Paciente, onResult: (Boolean) -> Unit) {
        Log.d("AgregarPaciente", "Datos del paciente: ${paciente.toString()}")
        instancePacientes.agregarPaciente(paciente).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("AgregarPaciente", "Paciente agregado correctamente")
                    onResult(true) // Indica que se agregó correctamente
                }else{
                    Log.e("AgregarPaciente", "Error al agregar paciente: ${response.code()}")
                    onResult(false) // Indica que hubo un error
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("AgregarPaciente", "Error al agregar", t)
                onResult(false) // Error de red u otro problema
            }
        })
    }

    fun obtenerPadecimientos(onResult: (List<PadecimientoP>?) -> Unit) {
        instancePacientes.getPadecimientos().enqueue(object : Callback<PadecimientosResponseP> {
            override fun onResponse(
                call: Call<PadecimientosResponseP>,
                response: Response<PadecimientosResponseP>
            ) {
                if (response.isSuccessful) {
                    // Devuelve la lista de padecimientos desde la propiedad `$values`
                    onResult(response.body()?.`$values`)
                } else {
                    Log.e("NotificationsViewModel", "Error en la respuesta")
                }
            }

            override fun onFailure(call: Call<PadecimientosResponseP>, t: Throwable) {
                Log.e("NotificationsViewModel", "Error al obtener padecimientos", t)
            }
        })
    }


}
