package com.example.health_plus_nurse.views.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.health_plus_nurse.databinding.FragmentNotificationsBinding
import com.example.health_plus_nurse.models.Paciente
import com.example.health_plus_nurse.models.PadecimientoP

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var padecimientosDisponibles: List<PadecimientoP>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        configurarAutoComplete()

        binding.btnAgregarPaciente.setOnClickListener {
            val paciente = createPacienteFromInput()
            Log.d("Paciente", paciente.toString())
            notificationsViewModel.agregarPaciente(
                paciente,
                onResult = {
                    Toast.makeText(requireContext(), "Paciente agregado exitosamente", Toast.LENGTH_SHORT).show()
                },
            )
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createPacienteFromInput(): Paciente {
        // Obtener los padecimientos seleccionados del MultiAutoCompleteTextView
        val padecimientosSeleccionados = binding.txtEnfermedades.text.toString()
            .split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .mapNotNull { nombre ->
                padecimientosDisponibles.find { it.nombrePadecimiento == nombre }
            }
        val idPadecimientos = padecimientosSeleccionados.map { it.idPadecimiento }
        val numPaciente = generateNumPaciente(binding.txtNombre.text.toString(), binding.txtTipoSangre.text.toString())

        return Paciente(
            idPaciente = 0,
            nombre = binding.txtNombre.text.toString(),
            primerApellido = binding.txt1er.text.toString(),
            segundoApellido = binding.txt2do.text.toString(),
            telefono = binding.txtTelefono.text.toString(),
            fechaNacimiento = binding.txtFechaN.text.toString(),
            calle = binding.txtCalle.text.toString(),
            numero = binding.txtNumeroCasa.text.toString(),
            colonia = binding.txtColonia.text.toString(),
            codigoPostal = binding.txtCP.text.toString(),
            numPaciente = numPaciente,
            altura = binding.txtAltura.text.toString(),  // Asegúrate de que esto sea un String
            peso = binding.txtPeso.text.toString(),  // Asegúrate de que esto sea un String
            tipoSangre = binding.txtTipoSangre.text.toString(),
            estatus = true,
            usuario1 = binding.txtRegistroUsuario.text.toString(),
            contrasenia = binding.txtRegistroContrasenia.text.toString(),
            max = 0, // Cambia estos valores si es necesario
            min = 0,  // Cambia estos valores si es necesario
            padecimientos = padecimientosSeleccionados, // Lista de objetos PadecimientoP
            idPadecimientos = idPadecimientos // Solo los IDs de los padecimientos
        )
    }


    private fun configurarAutoComplete() {
        notificationsViewModel.obtenerPadecimientos { padecimientos ->
            if (padecimientos != null) {
                padecimientosDisponibles = padecimientos // Guardar la lista de padecimientos disponibles

                // Extraer nombres de los padecimientos
                val nombresPadecimientos = padecimientos.map { it.nombrePadecimiento }

                // Crear un adaptador para el MultiAutoCompleteTextView
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    nombresPadecimientos
                )

                // Configurar el adaptador y el tokenizador
                binding.txtEnfermedades.setAdapter(adapter)
                binding.txtEnfermedades.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
            } else {
                // Manejar el caso de error (padecimientos nulos)
                Toast.makeText(
                    requireContext(),
                    "No se pudieron cargar los padecimientos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun generateNumPaciente(nombre: String, tipoSangre: String): String {
        // Generar un número aleatorio de 6 dígitos
        val randomNumber = (100000..999999).random()

        // Tomar la primera letra del tipo de sangre (asegurarse de que haya un tipo de sangre proporcionado)
        val tipoSangreLetra = if (tipoSangre.isNotEmpty()) tipoSangre[0].toUpperCase() else 'A'

        // Generar el número de paciente en el formato requerido: "PAN" + número aleatorio + tipo de sangre
        return "PAN$randomNumber$tipoSangreLetra"
    }
}
