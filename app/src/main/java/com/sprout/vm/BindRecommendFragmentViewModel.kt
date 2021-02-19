package com.sprout.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shop.net.Injection
import com.sprout.base.BaseViewModel
import com.sprout.model.RecommendData
import kotlinx.coroutines.launch

class BindRecommendFragmentViewModel : BaseViewModel(Injection.repository) {

    var recommendData: MutableLiveData<List<RecommendData>> = MutableLiveData()

    fun recommend() {
        viewModelScope.launch {

            val recommend = repository.recommend()
            if (recommend != null) {
                recommendData.postValue(recommend.data)
            }
        }
    }

}