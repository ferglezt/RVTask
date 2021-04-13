package com.example.encoratask

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CharacterService {

    @GET("character/")
    fun listCharacters(@Query("page") user: Int): Call<CharacterResponse>
}