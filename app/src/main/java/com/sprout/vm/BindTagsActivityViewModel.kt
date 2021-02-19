package com.sprout.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shop.net.Injection
import com.sprout.base.BaseViewModel
import com.sprout.model.BrandData
import com.sprout.model.GoodsData
import kotlinx.coroutines.launch

class BindTagsActivityViewModel : BaseViewModel(Injection.repository) {

    var brandList : MutableLiveData<BrandData> = MutableLiveData()
    var goodsList : MutableLiveData<GoodsData> = MutableLiveData()

    var bpage = 0
    var gpage = 0
    var size = 10

    fun getBrand(){
        viewModelScope.launch {
            val result = repository.getBrand(bpage, size)
            if(result!=null){
                brandList.postValue(result.data)
            }
        }
    }

    fun getGoods(){
        viewModelScope.launch {
            var result =repository.getGoods(gpage,size)
            if(result!=null){
                goodsList.postValue(result.data)
            }
        }
    }

}
