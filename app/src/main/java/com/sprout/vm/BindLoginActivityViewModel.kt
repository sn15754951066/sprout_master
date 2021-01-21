package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.LoginData
import com.shop.net.Injection
import com.sprout.base.BaseViewModel
import kotlinx.coroutines.launch

class BindLoginActivityViewModel: BaseViewModel(Injection.repository) {

    var data : MutableLiveData<LoginData> = MutableLiveData()

    fun login(map:  HashMap<String,String>){
        viewModelScope.launch {
            var result = repository.login(map)
            if (result!=null){
                data.postValue(result.data)
            }
        }
    }

}