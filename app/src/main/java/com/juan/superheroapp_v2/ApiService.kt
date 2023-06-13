package com.juan.superheroapp_v2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/2909185635881037/search/{name}")
    suspend fun getSuperHeroes(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>

    @GET("/api/2909185635881037/{id}")
    suspend fun getSuperHeroDetail(@Path("id") superHeroId: String): Response<SuperHeroDetailResponse>

    @GET("/api/2909185635881037/{id}/biography")
    suspend fun getBiography(@Path("id") biography: String): Response<BiographyResponse>

    @GET("/api/2909185635881037/{id}/appearance")
    suspend fun getAppearance(@Path("id") appearance: String): Response<AppearanceResponse>

    @GET("/api/2909185635881037/{id}/connections")
    suspend fun getConnections(@Path("id") connections: String): Response<ConnectionsResponse>

    @GET("/api/2909185635881037/{id}/work")
    suspend fun getWork(@Path("id") work: String): Response<WorkResponse>
    @GET("/api/2909185635881037/{id}/powerstats")
    suspend fun getPowerStats(@Path("id") powerStats: String): Response<PowerStatsResponse>


}