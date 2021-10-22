package com.example.plantixdemo.network

import com.example.plantixdemo.models.PlantixResponseModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = "https://dl.dropboxusercontent.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    )
    .build()

interface ApiKtService {

    @POST(GetDataEndPoint)
    suspend fun getData(): Response<PlantixResponseModel>

}

object ServiceApi{
    val retrofitService : ApiKtService by lazy { retrofit.create(ApiKtService::class.java) }
}

private const val GetDataEndPoint = "s/2iodh4vg0eortkl/facts.json"
