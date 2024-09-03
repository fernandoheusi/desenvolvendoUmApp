package br.com.fiap.desenvolvendoumapp.service

import br.com.fiap.desenvolvendoumapp.model.Event
import retrofit2.Call
import retrofit2.http.GET

interface EventService {

    //http://localhost:3000/events
    @GET("events")
    fun getEvents(): Call<List<Event>>
}