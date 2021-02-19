package com.sprout.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shop.net.Injection
import com.sprout.base.BaseViewModel
import com.sprout.model.ThemeData
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody

class BindSubnitMoreActivityViewModel : BaseViewModel(Injection.repository) {

    var state : MutableLiveData<Int> = MutableLiveData()

    /**
     * 提取动态数据
     */

    fun submitThrends(json:String){
        var body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json)
        viewModelScope.launch {
            var result = repository.submitTrends(body)
            if(result.errno == 0){
                state.postValue(200)
            }else{
                state.postValue(-1)
            }
        }
    }


}