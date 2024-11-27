package com.example.health_plus_nurse.views.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.health_plus_nurse.R
import com.example.health_plus_nurse.config.SessionManager
import com.example.health_plus_nurse.databinding.FragmentHomeBinding
import com.example.health_plus_nurse.models.Paciente
import com.example.health_plus_nurse.views.ui.recordatorio.RecordatorioActivity

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var listView: ListView
    private lateinit var session: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        session = SessionManager(requireContext())
        val idEnfermero = session.getEnfermeroId()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        listView = binding.listView
        homeViewModel.obtenerPacientes(idEnfermero)

        homeViewModel.pacientes.observe(viewLifecycleOwner) { pacientes ->
            val nombresPacientes = pacientes?.map { it.nombre } ?: emptyList()
            val adapter = object : ArrayAdapter<String>(requireContext(), R.layout.list_item_paciente, R.id.text_name, nombresPacientes) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    val paciente = pacientes?.get(position)

                    val textName = view.findViewById<TextView>(R.id.text_name)
                    val textId = view.findViewById<TextView>(R.id.text_id)
                    val iconRecordatorio = view.findViewById<ImageView>(R.id.icon_recordatorio)

                    textName.text = paciente?.nombre
                    textId.text = paciente?.numPaciente.toString()

                    iconRecordatorio.setOnClickListener {
                        paciente?.let {
                            val idPaciente = it.idPaciente
                            Log.d("HomeFragment", "Clic en recordatorio del paciente con ID: $idPaciente")

                            // Guardar el idPaciente en el SessionManager
                            val sessionManager = SessionManager(context)
                            sessionManager.savePaciente(idPaciente)

                            // Navegar a la actividad de recordatorio
                            val intent = Intent(context, RecordatorioActivity::class.java)
                            startActivity(intent)
                        }
                    }



                    // Aquí puedes agregar la lógica para mostrar u ocultar los iconos dependiendo del paciente
                    // iconWarning.visibility = if (paciente.necesitaAtencion) View.VISIBLE else View.INVISIBLE
                    // iconRecordatorio.visibility = if (paciente.tieneRecordatorio) View.VISIBLE else View.INVISIBLE

                    return view
                }
            }
            listView.adapter = adapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
