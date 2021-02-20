package com.sprout.ui.home

import android.widget.Toast
import androidx.lifecycle.Observer
import com.sprout.R
import com.sprout.base.BaseFragment
import com.sprout.databinding.FragmentRecommendBinding
import com.sprout.vm.BindRecommendFragmentViewModel
import kotlinx.android.synthetic.main.fragment_recommend.*


class recommendFragment : BaseFragment<BindRecommendFragmentViewModel, FragmentRecommendBinding>(
    R.layout.fragment_recommend,
    BindRecommendFragmentViewModel::class.java
) {


    override fun initView() {
        mViewModel.recommend()
    }

    override fun initVM() {
       mViewModel.recommendData.observe(this, Observer {

         for (i in it.indices){
             recommend_tab.addTab(recommend_tab.newTab().setText(it.get(i).name))
         }
       })
    }



    override fun initData() {
    }

    override fun initVariable() {
    }


}