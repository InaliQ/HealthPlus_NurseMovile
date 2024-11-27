package com.example.health_plus_nurse.config

import android.content.Context
import android.content.SharedPreferences
import com.example.health_plus_nurse.models.EnfermeroModel

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SessionPrefs", Context.MODE_PRIVATE)

    fun saveSession(idEnfermero: Int) {
        sharedPreferences.edit().putInt("idEnfermero", idEnfermero).apply()
    }

    fun getEnfermeroId(): Int {
        return sharedPreferences.getInt("idEnfermero", -1)
    }

    fun savePaciente(idPaciente: Int) {
        sharedPreferences.edit().putInt("idPaciente", idPaciente).apply()
    }

    fun getPacienteId(): Int {
        return sharedPreferences.getInt("idPaciente", -1)
    }
}