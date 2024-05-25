package br.com.fiapstatupone.vigiaseguro.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL = "https://api.adviceslip.com/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        fun getAdviceService(): AdviceService {
            return retrofitFactory.create(AdviceService::class.java)
        }


}