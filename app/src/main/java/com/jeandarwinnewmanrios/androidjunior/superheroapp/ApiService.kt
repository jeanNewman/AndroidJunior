package com.jeandarwinnewmanrios.androidjunior.superheroapp

import com.jeandarwinnewmanrios.androidjunior.superheroapp.models.ResultInfo
import com.jeandarwinnewmanrios.androidjunior.superheroapp.models.Root
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/10161223254704785/search/{name}")
    suspend fun getSuperheroes(@Path("name") superHeroName: String): Response<Root>

    @GET("/api/10161223254704785/{id}")
    suspend fun getSuperheroesById(@Path("id") superHeroId: String): Response<ResultInfo>

}