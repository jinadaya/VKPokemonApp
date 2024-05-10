package com.example.royaal.vkpokemonapp.network

import com.example.royaal.vkpokemonapp.network.model.details.PokemonResponse
import com.example.royaal.vkpokemonapp.network.model.main.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") pageSize: Int = 24,
    ): Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String,
    ): Response<PokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int,
    ): Response<PokemonResponse>
}