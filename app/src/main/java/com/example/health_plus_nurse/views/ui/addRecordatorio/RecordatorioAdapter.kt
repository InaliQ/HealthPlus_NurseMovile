package com.example.health_plus_nurse.views.ui.addRecordatorio

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.health_plus_nurse.R
import com.example.health_plus_nurse.models.Recordatorio
import com.example.health_plus_nurse.models.RecordatorioResponse

class RecordatorioAdapter(
    context: Context,
    private val recordatorios: List<Recordatorio>  // Aquí estamos usando List<Recordatorio>, no RecordatorioResponse
) : ArrayAdapter<Recordatorio>(context, 0, recordatorios) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_recordatorio, parent, false)

        val recordatorio = recordatorios[position]

        // Aquí asignamos los datos del recordatorio a las vistas correspondientes
        val medicamentoTextView = view.findViewById<TextView>(R.id.descripcionTextView)
        val fechaInicioTextView = view.findViewById<TextView>(R.id.fechaInicioTextView)

        medicamentoTextView.text = recordatorio.medicamento
        fechaInicioTextView.text = recordatorio.fechaInicio

        return view
    }
}

