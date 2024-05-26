package br.com.mindbox.service

import br.com.fiapstatupone.mindbox.model.Advice
import retrofit2.Call
import retrofit2.http.GET

interface AdviceService {

    //https://api.adviceslip.com/advice

    @GET("advice")
    fun getAdvice(): Call<Advice>

}