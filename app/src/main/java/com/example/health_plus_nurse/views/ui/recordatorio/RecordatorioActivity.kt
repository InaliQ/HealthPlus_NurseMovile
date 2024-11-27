package com.example.health_plus_nurse.views.ui.recordatorio

import com.example.health_plus_nurse.views.ui.addRecordatorio.RecordatorioAddActivity
import RetrofitClient.instanceRecodatorio
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.health_plus_nurse.R
import com.example.health_plus_nurse.config.SessionManager
import com.example.health_plus_nurse.models.RecordatorioResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordatorioActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordatorio)
        sessionManager = SessionManager(this)


        val idPaciente = sessionManager.getPacienteId()
        loadRecordatorios(idPaciente)

        val btnIrRecordatorio = findViewById<Button>(R.id.btnIrRecordatorio)
        btnIrRecordatorio.setOnClickListener {
            val intent = Intent(this, RecordatorioAddActivity::class.java)

            // Iniciar la actividad
            startActivity(intent)
        }



    }

    private fun loadRecordatorios(idPaciente: Int) {
        // Llamamos al servicio Retrofit
        instanceRecodatorio.getRecordatorios(idPaciente).enqueue(object : Callback<RecordatorioResponse> {
            override fun onResponse(call: Call<RecordatorioResponse>, response: Response<RecordatorioResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { recordatorioResponse ->
                        val ultimaAlerta = recordatorioResponse.ultimaAlerta
                        val ultimoRecordatorio = recordatorioResponse.ultimoRecordatorio
                        val ultimaAlertaTextView: TextView = findViewById(R.id.ultimaAlertaTextView)
                        val ultimoRecordatorioTextView: TextView = findViewById(R.id.ultimoRecordatorioTextView)

                        ultimaAlerta?.let {
                            ultimaAlertaTextView.text = "Última Alerta: ${it.descripcion} - ${it.fechaHora}"
                        }
                        ultimoRecordatorio?.let {
                            ultimoRecordatorioTextView.text = "Medicamento: ${it.medicamento} - Hora de Inicio: ${it.fechaInicio}"
                        }
                    }
                } else {
                    Toast.makeText(this@RecordatorioActivity, "Error al cargar los recordatorios", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RecordatorioResponse>, t: Throwable) {
                Toast.makeText(this@RecordatorioActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }


}
