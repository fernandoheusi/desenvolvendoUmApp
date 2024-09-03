package br.com.fiap.desenvolvendoumapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    // val URL é o ipv4 da sua maquina
    private val URL = "http://192.168.5.31:3000/"
    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCategoriesService(): CategoryService {
        return retrofitFactory.create(CategoryService::class.java)
    }
    fun getEventsService(): EventService {
        return retrofitFactory.create(EventService::class.java)
    }
}