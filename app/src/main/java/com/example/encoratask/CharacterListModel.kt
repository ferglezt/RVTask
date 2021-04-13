package com.example.encoratask

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class CharacterListModel : CharacterListMvp.Model {

    var cachedResponse : CharacterResponse? = null
    var currentPage : Int = 1;

    override fun loadCharacters(callback : (List<Character>?) -> Unit, errorCallback : (Throwable?) -> Unit) {
        loadCharacters(currentPage, callback, errorCallback)
    }

    override fun loadNextPage(callback : (List<Character>?) -> Unit, errorCallback : (Throwable?) -> Unit) {
        loadCharacters(++currentPage, callback, errorCallback)
    }

    private fun loadCharacters(page: Int, callback : (List<Character>?) -> Unit, errorCallback : (Throwable?) -> Unit) {
        val call = getCharacterService().listCharacters(page)
        call.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                cachedResponse = response.body()
                callback(cachedResponse?.results)
            }
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                errorCallback(t)
            }
        })
    }

    private fun getCharacterService() : CharacterService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CharacterService::class.java)
    }
}