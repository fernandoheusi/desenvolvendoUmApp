package br.com.fiap.desenvolvendoumapp.service

import br.com.fiap.desenvolvendoumapp.model.Category
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService {

    //http://localhost:3000/categories
    @GET("categories")
    fun getCategories(): Call<List<Category>>
}