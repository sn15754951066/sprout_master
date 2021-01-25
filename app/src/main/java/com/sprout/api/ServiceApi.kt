package com.shop.api

import com.example.myapplication.data.LoginData
import com.shop.net.BaseResp
import com.sprout.model.RecommendData
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ServiceApi {


//    @POST("auth/refreshToken")  //刷新token
//    suspend fun refreshToken(): BaseResp<String>

    //登录
    @POST("auth/login")
    @FormUrlEncoded
    suspend fun login(@FieldMap map: HashMap<String,String>) : BaseResp<LoginData>

    //频道分类
    @GET("channel/channel")
    suspend fun recommend() : BaseResp<List<RecommendData>>
}