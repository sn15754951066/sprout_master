package com.sprout.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sprout.MainActivity
import com.sprout.R
import com.sprout.base.BaseFragment
import com.sprout.databinding.FragmentHomeBinding
import com.sprout.vm.BindHomeFragmentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<BindHomeFragmentViewModel, FragmentHomeBinding>(
    R.layout.fragment_home,
    BindHomeFragmentViewModel::class.java
) {

    companion object{
        val instance:HomeFragment by lazy { HomeFragment() }
    }


    override fun initView() {


        //tab与fragment绑定
        var list: List<Fragment> = mViewModel.fragment

        home_vp!!.adapter = ViewPage(childFragmentManager, list)

        home_tab.setupWithViewPager(home_vp)

        home_tab.getTabAt(0)?.setText("同城")
        home_tab.getTabAt(1)?.setText("关注")
        home_tab.getTabAt(2)?.setText("推荐")

    }

    class ViewPage(fragmentManager: FragmentManager, val list: List<Fragment>) :
        FragmentStatePagerAdapter(fragmentManager) {

        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(position: Int): Fragment {
            return list.get(position)
        }

    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {

    }

}