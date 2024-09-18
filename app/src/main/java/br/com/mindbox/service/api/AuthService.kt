package br.com.mindbox.service.api

import br.com.mindbox.dto.auth.LoginDTO
import br.com.mindbox.model.user.MyUserResponseDTO
import br.com.mindbox.model.user.TokenUserResponseDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body loginDTO: LoginDTO): Response<TokenUserResponseDTO>

}