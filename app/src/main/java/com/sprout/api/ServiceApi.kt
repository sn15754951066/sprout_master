package com.shop.api

import com.example.myapplication.data.LoginData
import com.shop.net.BaseResp
import com.sprout.model.BrandData
import com.sprout.model.GoodsData
import com.sprout.model.RecommendData
import com.sprout.model.ThemeData
import retrofit2.http.*


interface ServiceApi {


//    @POST("auth/refreshToken")  //刷新token
//    suspend fun refreshToken(): BaseResp<String>

    //登录
    @POST("auth/login")
    @FormUrlEncoded
    suspend fun login(@FieldMap map: HashMap<String, String>): BaseResp<LoginData>

    //频道分类
    @GET("channel/channel")
    suspend fun recommend(): BaseResp<List<RecommendData>>

    //获取品牌数据 http://sprout.cdwan.cn/api/tag/brand?page=1&size=10
    @GET("tag/brand")
    suspend fun getBrand(@Query("page") page: Int, @Query("size") size: Int): BaseResp<BrandData>


    //获取商品数据 http://sprout.cdwan.cn/api/tag/goods?page=1&size=10
    @GET("tag/goods")
    suspend fun getGoods(@Query("page") page: Int, @Query("size") size: Int): BaseResp<GoodsData>

    //获取主题数据 http://sprout.cdwan.cn/api/theme/getTheme
    @GET("theme/getTheme")
    suspend fun getTheme():BaseResp<List<ThemeData>>
}