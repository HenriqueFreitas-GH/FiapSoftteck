package br.com.fiap.fiapsoftteck.api

import br.com.fiap.fiapsoftteck.model.Question
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("submit")
    suspend fun sendAnswers(@Body answers: List<Question>)
}

object RetrofitClient {
    private const val BASE_URL = "https://mockapi.io/helptek/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

