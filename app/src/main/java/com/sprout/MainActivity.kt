package com.sprout

import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.sprout.base.BaseActivity
import com.sprout.databinding.ActivityMainBinding
import com.sprout.ui.HomeFragment
import com.sprout.ui.InformationFragment
import com.sprout.ui.MeFragment
import com.sprout.ui.SearchFragment
import com.sprout.ui.more.MoreEditorActivity
import com.sprout.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(
    R.layout.activity_main,
    MainViewModel::class.java
), View.OnClickListener {


    override fun initData() {

    }

    override fun initVM() {

    }

    override fun initVariable() {

    }

    override fun initView() {


        var list = ArrayList<Fragment>()
        list.add(HomeFragment.instance)
        list.add(SearchFragment.instance)
        list.add(InformationFragment.instance)
        list.add(MeFragment.instance)

        pager.adapter = ViewPage(supportFragmentManager, list)

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                resetImg()
                when(position){
                    0-> img_home.setImageResource(R.mipmap.main_nav_home_hl)
                    1-> img_discover.setImageResource(R.mipmap.main_nav_discover_hl)
                    2-> img_message.setImageResource(R.mipmap.main_nav_message_hl)
                    3-> img_mine.setImageResource(R.mipmap.main_nav_mine_hl)
                }  }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

        mDataBinding.layoutHome.setOnClickListener(this)
        mDataBinding.layoutDiscover.setOnClickListener(this)
        mDataBinding.layoutMore.setOnClickListener(this)
        mDataBinding.layoutMessage.setOnClickListener(this)
        mDataBinding.layoutMine.setOnClickListener(this)

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

    override fun onClick(v: View?) {
        resetImg()
        when (v!!.id) {
            R.id.layout_home -> {
                mDataBinding.imgHome.setImageResource(R.mipmap.main_nav_home_hl)
                pager.setCurrentItem(0)
            }
            R.id.layout_discover -> {
                mDataBinding.imgDiscover.setImageResource(R.mipmap.main_nav_discover_hl)
                pager.setCurrentItem(1)
            }
            R.id.layout_more -> {
                var intent = Intent(this, MoreEditorActivity::class.java)
                startActivity(intent)
            }
            R.id.layout_message -> {
                mDataBinding.imgMessage.setImageResource(R.mipmap.main_nav_message_hl)
                pager.setCurrentItem(2)
            }
            R.id.layout_mine -> {
                mDataBinding.imgMine.setImageResource(R.mipmap.main_nav_mine_hl)
                pager.setCurrentItem(3)
            }
        }
    }

    private fun resetImg() {
        mDataBinding.imgHome.setImageResource(R.mipmap.main_nav_home_normal)
        mDataBinding.imgDiscover.setImageResource(R.mipmap.main_nav_discover_normal)
        mDataBinding.imgMessage.setImageResource(R.mipmap.main_nav_message_normal)
        mDataBinding.imgMine.setImageResource(R.mipmap.main_nav_mine_normal)
    }

}