package com.sprout.ui.more

import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.map.MyLocationData
import com.sprout.R
import com.sprout.base.BaseActivity
import com.sprout.databinding.ActivityAddressBinding
import com.sprout.vm.BindAddressActivityViewModel
import io.nlopez.smartlocation.SmartLocation
import kotlinx.android.synthetic.main.activity_address.*
import java.io.IOException

class AddressActivity : BaseActivity<BindAddressActivityViewModel, ActivityAddressBinding>(
    R.layout.activity_address,
    BindAddressActivityViewModel::class.java
) {




    override fun initView() {



        SmartLocation.with(this).location()
            .start { location ->
                location_text.text = location.latitude.toString()
                // 解析地址并显示 69
                val geoCoder = Geocoder(this)
                try {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val list =
                        geoCoder.getFromLocation(latitude.toDouble(), longitude.toDouble(), 2)
                    for (i in list.indices) {
                        val address = list[i]
                        geofence_text.text = address.countryName
                        Toast.makeText(
                            this,
                            address.countryName + address.adminArea + address.featureName,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: IOException) {
                }


            }

//        //不显示
//        ll_noAddress.setOnClickListener {
//
//            intent.putExtra("lat", 0.0)
//            intent.putExtra("lng", 0.0)
//            intent.putExtra("address", "address")
//            setResult(5, intent)
//            finish()
//
//        }
    }

    override fun initData() {

    }

    override fun initVM() {
    }

    override fun initVariable() {
    }

}