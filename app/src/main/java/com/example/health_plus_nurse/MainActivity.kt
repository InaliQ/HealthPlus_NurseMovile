package com.example.health_plus_nurse

import RetrofitClient
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.health_plus_nurse.config.SessionManager
import com.example.health_plus_nurse.models.EnfermeroModel
import com.example.health_plus_nurse.models.LoginRequest
import com.example.health_plus_nurse.views.Inicio
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var txtUsuario: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        txtUsuario = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        session = SessionManager(this)

        btnLogin.setOnClickListener {
            val usuario = txtUsuario.text.toString().trim()
            val contrasenia = txtPassword.text.toString().trim()

            if (usuario.isNotEmpty() && contrasenia.isNotEmpty()) {
                login(usuario, contrasenia)
            } else {
                Toast.makeText(this, "Por favor, ingresa usuario y contraseña.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(usuario: String, contrasenia: String) {
        Toast.makeText(this, "Iniciando Sesión...", Toast.LENGTH_SHORT).show()
        val request = LoginRequest(NombreUsuario = usuario, contrasenia = contrasenia)

        RetrofitClient.instance.postLogin(request).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val stringResponse = response.body()?.string()
                    val gson = Gson()
                    val usuarioModel = gson.fromJson(stringResponse, EnfermeroModel::class.java)

                    if (usuarioModel != null) {
                        session.saveSession(usuarioModel.idEnfermero)
                        Toast.makeText(this@MainActivity, "Bienvenido, Estimado Enfermero", Toast.LENGTH_SHORT).show()
                        Log.d("MainActivity", "ID del Enfermero: ${usuarioModel.idEnfermero}")
                        session.saveSession(usuarioModel.idEnfermero)

                        // Navegar a la pantalla de Inicio
                        val intent = Intent(this@MainActivity, Inicio::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@MainActivity, "Error al iniciar sesión. Datos inválidos.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("MainActivity", "Error en la respuesta: ${response.errorBody()?.string()}")
                    Toast.makeText(this@MainActivity, "Error: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
