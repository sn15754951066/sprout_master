package com.shop.net.repository

import com.example.myapplication.net.RetrofitFactory
import com.shop.api.ServiceApi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 数据仓库
 * 用来连接ViewModel和Model
 * 定义业务相关的网络请求接口的api
 */
class SystemRepository {

    private lateinit var serviceApi: ServiceApi

    //初始化的方法
    init {
        serviceApi = RetrofitFactory.instance.create(ServiceApi::class.java)

    }

//    /**
//     * 刷新token
//     */
//    suspend fun refreshToken() = withContext(Dispatchers.IO){
//        serviceApi.refreshToken()
//    }

    /**
     * 刷新token
     */
    suspend fun login(map : HashMap<String,String>) = withContext(Dispatchers.IO){
        serviceApi.login(map)
    }

    /**
     * 频道分类
     */
    suspend fun recommend()= withContext(Dispatchers.IO){
        serviceApi.recommend()
    }

    /**
     * 获取数据
     */
    suspend fun getBrand(page:Int,size:Int)= withContext(Dispatchers.IO){
        serviceApi.getBrand(page,size)
    }

    /**
     *
     */
    suspend fun getGoods(page: Int,size: Int)= withContext(Dispatchers.IO){
        serviceApi.getGoods(page,size)
    }



}