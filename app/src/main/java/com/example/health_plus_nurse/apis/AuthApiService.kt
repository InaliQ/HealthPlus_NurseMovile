package com.example.health_plus_nurse.apis

import com.example.health_plus_nurse.models.LoginRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("enfermeros/Auth/login")
    fun postLogin(
        @Body loginRequest: LoginRequest
    ): Call<ResponseBody>
}