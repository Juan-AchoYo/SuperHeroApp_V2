package com.juan.superheroapp_v2

import com.google.gson.annotations.SerializedName


data class SuperHeroData(
    @SerializedName("response") val response: String,
    @SerializedName("results") val results: List<SuperHeroItem>
)
data class SuperHeroItem(
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
    @SerializedName("image") val image:SuperHeroImage
)
data class SuperHeroImage(
    @SerializedName("url") val url:String
)