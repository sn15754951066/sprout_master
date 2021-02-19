package com.sprout.vm

import androidx.fragment.app.Fragment
import com.shop.net.Injection
import com.sprout.R
import com.sprout.base.BaseViewModel
import com.sprout.ui.home.AttentionFragment
import com.sprout.ui.home.LocalFragment
import com.sprout.ui.home.recommendFragment

class BindHomeFragmentViewModel : BaseViewModel(Injection.repository) {

    var fragment: MutableList<Fragment> = mutableListOf()

    init {
        fragment.add(LocalFragment())
        fragment.add(AttentionFragment())
        fragment.add(recommendFragment())

    }

}