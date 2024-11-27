package com.example.health_plus_nurse.views.ui.addRecordatorio

import RetrofitClient.instanceRecodatorio
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.health_plus_nurse.R
import com.example.health_plus_nurse.config.SessionManager
import com.example.health_plus_nurse.models.RecordatorioRequest
import com.example.health_plus_nurse.models.RecordatorioResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class RecordatorioAddActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var etMedicamento: EditText
    private lateinit var etCantidadMedicamento: EditText
    private lateinit var btnAgregarRecordatorio: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordatorio_add)
        sessionManager = SessionManager(this)
        btnAgregarRecordatorio = findViewById(R.id.btnAgregarRecor)
        etMedicamento = findViewById(R.id.txtMedicamento)
        etCantidadMedicamento = findViewById(R.id.txtCantidad)
        btnAgregarRecordatorio.setOnClickListener {
            val medicamento = etMedicamento.text.toString()
            val cantidadMedicamento = etCantidadMedicamento.text.toString()
            val fechaInicio = LocalDate.now().toString()
            val fechaFin = LocalDate.now().toString()
            val estatus = false
            // Simular IDs para el ejemplo, puedes cambiarlos según tu aplicación
            val idEnfermero = sessionManager.getEnfermeroId()
            val idPaciente = sessionManager.getPacienteId()

            val recordatorioRequest = RecordatorioRequest(
                medicamento,
                cantidadMedicamento,
                fechaInicio,
                fechaFin,
                estatus,
                idEnfermero,
                idPaciente
            )

            agregarRecordatorio(recordatorioRequest)
        }
    }

    private fun agregarRecordatorio(recordatorioRequest: RecordatorioRequest) {
        instanceRecodatorio.agregarRecordatorio(recordatorioRequest).enqueue(object : Callback<RecordatorioResponse> {
            override fun onResponse(call: Call<RecordatorioResponse>, response: Response<RecordatorioResponse>) {
                if (response.isSuccessful) {
                    val respuesta = response.body()
                    // Aquí puedes mostrar un mensaje o realizar alguna acción en la UI
                    Log.d("Recordatorio", "Recordatorio agregado correctamente")
                } else {
                    // Maneja el error en caso de que la respuesta no sea exitosa
                    Log.e("Recordatorio", "Error al agregar el recordatorio: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RecordatorioResponse>, t: Throwable) {
                // Maneja el error de red o cualquier otra falla
                println("Error de red: ${t.message}")
            }
        })
    }
}
