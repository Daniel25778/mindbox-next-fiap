package br.com.mindbox.service.api

import android.content.Context
import br.com.mindbox.dto.auth.LoginDTO
import br.com.mindbox.util.context.MyApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthRepository {
    private val authService: AuthService = ApiClient.createAuthClient().create(AuthService::class.java)

    suspend fun login(email: String, password: String): Result<String> {
        println("teste daaaaaa1")
        return try {
            println("teste daaaaaa2")
            val response = authService.login(LoginDTO(email, password))
            println("Código de resposta: ${response.code()}")
            println("Mensagem de resposta: ${response.message()}")
            if (response.isSuccessful) {
                val token = response.body()?.token
                if (token != null) {
                    saveToken(token)
                    Result.success(token)
                } else {
                    Result.failure(Exception("Token não encontrado na resposta"))
                }
            } else {
                Result.failure(Exception("Erro: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun saveToken(token: String) {
        val context = MyApp.context
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("auth_token", token)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("auth_token", null) // Use a mesma chave que foi usada para salvar
    }
}
