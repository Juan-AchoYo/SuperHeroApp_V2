package com.juan.superheroapp_v2

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("name") val name:String,
    @SerializedName("image") val image: SuperHeroImageResponse
)