package br.com.fiap.desenvolvendoumapp.service

import br.com.fiap.desenvolvendoumapp.model.Event
import retrofit2.Call
import retrofit2.http.GET

interface EventService {

    //http://localhost:3000/events
    @GET("events")
    fun getEvents(): Call<List<Event>>
    @GET("futsal")
    fun getEventsFutsal(): Call<List<Event>>
    @GET("corrida")
    fun getEventsCorrida(): Call<List<Event>>
    @GET("volei")
    fun getEventsVolei(): Call<List<Event>>
    @GET("basket")
    fun getEventsBasket(): Call<List<Event>>
    @GET("marciais")
    fun getEventsMarciais(): Call<List<Event>>
    @GET("fit")
    fun getEventsFit(): Call<List<Event>>
    @GET("yoga")
    fun getEventsYoga(): Call<List<Event>>
    @GET("natacao")
    fun getEventsNatacao(): Call<List<Event>>
    @GET("ginastica")
    fun getEventsGinastica(): Call<List<Event>>
    @GET("hiphop")
    fun getEventsHiphop(): Call<List<Event>>
}