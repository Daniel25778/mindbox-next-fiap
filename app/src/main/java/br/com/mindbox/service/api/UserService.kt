package br.com.mindbox.service.api

import br.com.mindbox.model.user.MyUserResponseDTO
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("user/me/")
    fun getMe(): Call<MyUserResponseDTO>

}