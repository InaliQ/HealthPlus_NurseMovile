package com.example.health_plus_nurse.views.ui.dashboard

import RetrofitClient.instanceDasboard
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.health_plus_nurse.models.PadecimientoResponse
import com.example.health_plus_nurse.models.PadecimientoXpersona
import com.example.health_plus_nurse.models.PadecimientoXpersonaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {

    private val _padecimientos = MutableLiveData<List<PadecimientoResponse>>()
    private val _padecimientosXedad = MutableLiveData<List<PadecimientoXpersonaResponse>>()

    val padecimientos: LiveData<List<PadecimientoResponse>> get() = _padecimientos
    val padecimientosXedad: LiveData<List<PadecimientoXpersonaResponse>> get() = _padecimientosXedad

    fun pacientesXpadecimiento() {
        instanceDasboard.getPacientesXPadecimiento().enqueue(object : Callback<List<PadecimientoResponse>> {
            override fun onResponse(
                call: Call<List<PadecimientoResponse>>,
                response: Response<List<PadecimientoResponse>>
            ) {
                if (response.isSuccessful) {
                    _padecimientos.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<PadecimientoResponse>>, t: Throwable) {
                // Manejar errores
            }
        })
    }

    fun padecimientoXedad() {
        Log.d("DashboardViewModel", "Realizando la solicitud al servidor...")
        instanceDasboard.getPadecimientosXEdad().enqueue(object : Callback<PadecimientoXpersonaResponse> { // Cambiado a PadecimientoXpersonaResponse
            override fun onResponse(
                call: Call<PadecimientoXpersonaResponse>,
                response: Response<PadecimientoXpersonaResponse>
            ) {
                Log.d("DashboardViewModel", "Respuesta del servidor: ${response.body()}")
                if (response.isSuccessful) {
                    // Accedemos a la lista $values dentro de la respuesta
                    _padecimientosXedad.value = response.body()
                }
            }

            override fun onFailure(call: Call<PadecimientoXpersonaResponse>, t: Throwable) {
                Log.e("DashboardViewModel", "Error al obtener los datos: ${t.message}")
            }
        })
    }

}
