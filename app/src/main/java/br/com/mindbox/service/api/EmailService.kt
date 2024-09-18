package br.com.mindbox.service.api;

import br.com.mindbox.model.email.EmailResponseDTO
import retrofit2.Call
import retrofit2.http.GET;
import retrofit2.http.Path

interface EmailService {
    @GET("email")
    fun getAll(): Call<List<EmailResponseDTO>>

    @GET("email/{id}")
    fun findById(@Path("id") id: Long): Call<EmailResponseDTO>
}