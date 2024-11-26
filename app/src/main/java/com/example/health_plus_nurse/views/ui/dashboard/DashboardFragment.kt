package com.example.health_plus_nurse.views.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.health_plus_nurse.R
import com.example.health_plus_nurse.models.PadecimientoResponse
import com.example.health_plus_nurse.models.PadecimientoXpersonaResponse
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var barChart: BarChart
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        barChart = view.findViewById(R.id.barChart)
        pieChart = view.findViewById(R.id.pieChart)

        // Observa los datos
        viewModel.padecimientos.observe(viewLifecycleOwner) { padecimientos ->
            if (padecimientos.isNotEmpty()) {
                configureBarChart(padecimientos)
            }
        }

        viewModel.padecimientosXedad.observe(viewLifecycleOwner) { padecimientosXedad ->
            if (padecimientosXedad.isNotEmpty()) {
                setupPieChart(padecimientosXedad)
            }
        }

        // Llama a la función para obtener los datos
        viewModel.pacientesXpadecimiento()
        viewModel.padecimientoXedad()

        return view
    }

    private fun configureBarChart(padecimientos: List<PadecimientoResponse>) {
        val barEntries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()

        // Agrega los datos al gráfico
        padecimientos.forEachIndexed { index, item ->
            barEntries.add(BarEntry(index.toFloat(), item.cantidadPacientes.toFloat()))
            labels.add(item.padecimientoNombre)
        }

        val barDataSet = BarDataSet(barEntries, "Cantidad de Pacientes")
        barDataSet.color = resources.getColor(R.color.teal_700) // Cambia el color de las barras
        val data = BarData(barDataSet)

        barChart.data = data
        barChart.description.isEnabled = false // Desactiva la descripción
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels) // Etiquetas en el eje X
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.setDrawGridLines(false) // Desactiva las líneas de fondo
        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisRight.isEnabled = false // Desactiva el eje derecho
        barChart.invalidate() // Refresca el gráfico
    }


    private fun setupPieChart(data: List<PadecimientoXpersonaResponse>) {
        // Asegúrate de que la lista data tenga elementos y que cada PadecimientoXpersona tenga su lista de padecimientos
        val entries = data.flatMap { padecimientoXpersona ->
            padecimientoXpersona.`$id`.map {
                // Crear una PieEntry por cada elemento en la lista 'value'
                PieEntry(it..toFloat(), "Edad: ${it.idPadecimiento}")
            }
        }

        // Configura el dataset para el gráfico de torta
        val dataSet = PieDataSet(entries, "Padecimientos por Edad").apply {
            colors = listOf(
                Color.rgb(244, 67, 54),  // Rojo
                Color.rgb(33, 150, 243), // Azul
                Color.rgb(76, 175, 80),  // Verde
                Color.rgb(255, 235, 59), // Amarillo
                Color.rgb(156, 39, 176), // Morado
                Color.rgb(255, 152, 0)   // Naranja
            )
            valueTextColor = Color.WHITE
            valueTextSize = 12f
        }

        // Configura los datos del gráfico de torta
        val pieData = PieData(dataSet).apply {
            setValueTextSize(14f)
            setValueTextColor(Color.WHITE)
        }

        // Configura el gráfico de torta
        pieChart.data = pieData
        pieChart.description.isEnabled = false
        pieChart.isDrawHoleEnabled = true
        pieChart.holeRadius = 40f
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.setEntryLabelTextSize(12f)
        pieChart.animateY(1000, Easing.EaseInOutQuad)

        // Redibuja el gráfico
        pieChart.invalidate()
    }



}
