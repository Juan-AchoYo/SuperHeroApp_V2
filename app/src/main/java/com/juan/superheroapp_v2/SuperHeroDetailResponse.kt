package com.juan.superheroapp_v2

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: SuperHeroImageResponse
)

data class BiographyResponse(
    @SerializedName("name") val name: String,
    @SerializedName("full-name") val fullName: String,
    @SerializedName("alter-egos") val alterEgos: String,
    @SerializedName("aliases") val aliases: List<String>,
    @SerializedName("place-of-birth") val placeOfBirth: String,
    @SerializedName("first-appearance") val firstAppearance: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("alignment") val alignment: String
)

data class AppearanceResponse(
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("race") val race: String,
    @SerializedName("height") val height: List<String>,
    @SerializedName("weight") val weight: List<String>,
    @SerializedName("eye-color") val eyeColor: String,
    @SerializedName("hair-color") val hairColor: String
)

data class ConnectionsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("group-affiliation") val groupAffiliation: String,
    @SerializedName("relatives") val relatives: String
)

data class WorkResponse(
    @SerializedName("name") val name: String,
    @SerializedName("occupation") val occupation: String,
    @SerializedName("base") val base: String
)

data class PowerStatsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)