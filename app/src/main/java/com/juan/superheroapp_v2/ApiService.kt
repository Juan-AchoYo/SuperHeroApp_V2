package com.juan.superheroapp_v2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/2909185635881037/search/{name}")
    suspend fun getSuperHeroes(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>

    @GET("/api/2909185635881037/{id}")
    suspend fun getSuperHeroDetail(@Path("id") superHeroId:String):Response<SuperHeroDetailResponse>
}