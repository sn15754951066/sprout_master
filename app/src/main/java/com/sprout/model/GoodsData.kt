package com.sprout.model


data class GoodsData(
    val count: Int,
    val currentPage: Int,
    val `data`: List<DataX>,
    val pageSize: Int,
    val totalPages: Int
){
    data class DataX(
        val id: Int,
        val name: String,
        val sort: Int,
        val title: String,
        val url: Any
    )
}

