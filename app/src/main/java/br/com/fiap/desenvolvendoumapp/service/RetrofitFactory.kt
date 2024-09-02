package br.com.fiap.desenvolvendoumapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL = "https://xxx.xxx.xx.x:3000/"
    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCategoriesService(): CategoryService {
        return retrofitFactory.create(CategoryService::class.java)
    }
}