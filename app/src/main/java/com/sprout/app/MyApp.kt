package com.example.myapplication.app

import android.app.Application
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.shop.utils.MyMmkv

class MyApp:Application() {

    companion object{
        var instance:MyApp? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MyMmkv.initMMKV()
        //initMap()
    }

    //初始化地图
     fun initMap() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this)
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL)
    }

}