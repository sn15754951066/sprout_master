package com.example.myapplication.app

import android.location.Address
import retrofit2.http.GET

class Constants {

    companion object{
        val token:String = "token"
        //基础地址
        val BASE_URL:String = "http://sprout.cdwan.cn/api/"

        var Address_URL:String="https://api.epmap.org/api/v1/air/"


    }
}