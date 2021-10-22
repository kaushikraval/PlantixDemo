package com.example.plantixdemo.models


import com.google.gson.annotations.SerializedName

data class PlantixResponseModel(
    @SerializedName("rows")
    val rows: List<Row>?,
    @SerializedName("title")
    val title: String?
)

data class Row(
    @SerializedName("description")
    val description: String?,
    @SerializedName("imageHref")
    val imageHref: String?,
    @SerializedName("title")
    val title: String?
)