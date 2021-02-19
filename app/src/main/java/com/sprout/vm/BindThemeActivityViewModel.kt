package com.sprout.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shop.net.Injection
import com.sprout.base.BaseViewModel
import com.sprout.model.ThemeData
import kotlinx.coroutines.launch

class BindThemeActivityViewModel :  BaseViewModel(Injection.repository){

    /**
     * 获取主题数据
     */
    var themeData: MutableLiveData<List<ThemeData>> = MutableLiveData()

    fun getTheme() {
        viewModelScope.launch {
            val result = repository.getTheme()
            if (result != null) {
                themeData.postValue(result.data)
            }
        }
    }
}