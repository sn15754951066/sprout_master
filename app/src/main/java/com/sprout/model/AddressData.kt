package com.sprout.model

data class AddressData(
    val `data`: List<Data>,
    val success: Boolean
)

data class Data(
    val city: String,
    val city_code: String,
    val lat: Double,
    val lng: Double,
    val station: String,
    val station_code: String
)