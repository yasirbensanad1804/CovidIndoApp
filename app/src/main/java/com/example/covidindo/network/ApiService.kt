package com.example.covidindo.network

import com.example.covidindo.model.ResponseProv
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET("provinsi")
    fun getAllNegara() : Call<ResponseProv>
}
interface InfoService{
    @GET
    fun getInfoService(@Url url: String?)
}
object RetrofitBuilder{
    private val okHttp = OkHttpClient().newBuilder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://indonesia-covid-19.mathdro.id/api/")
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


