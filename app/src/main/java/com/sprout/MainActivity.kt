package com.sprout

import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sprout.base.BaseActivity
import com.sprout.databinding.ActivityMainBinding
import com.sprout.ui.HomeFragment
import com.sprout.ui.InformationFragment
import com.sprout.ui.MeFragment
import com.sprout.ui.SearchFragment
import com.sprout.ui.more.MoreEditorActivity
import com.sprout.vm.MainViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(
    R.layout.activity_main,
    MainViewModel::class.java
), View.OnClickListener {

    lateinit var homeFragment:Fragment
    lateinit var searchFragment:Fragment
    lateinit var informationFragment:Fragment
    lateinit var meFragment:Fragment

    lateinit var transaction: FragmentTransaction



    override fun initData() {

    }

    override fun initVM() {

    }

    override fun initVariable() {

    }

    override fun initView() {

        homeFragment = HomeFragment.instance
        searchFragment = SearchFragment.instance
        informationFragment = InformationFragment.instance
        meFragment = MeFragment.instance

        //初始化第一个fragment
        transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragments,homeFragment)

        transaction.commit()



        mDataBinding.layoutHome.setOnClickListener(this)
        mDataBinding.layoutDiscover.setOnClickListener(this)
        mDataBinding.layoutMore.setOnClickListener(this)
        mDataBinding.layoutMessage.setOnClickListener(this)
        mDataBinding.layoutMine.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        resetImg()
        when(v!!.id){
            R.id.layout_home -> {
                mDataBinding.imgHome.setImageResource(R.mipmap.main_nav_home_hl)
                transaction.replace(R.id.fragments,homeFragment)
            }
            R.id.layout_discover -> {
                mDataBinding.imgDiscover.setImageResource(R.mipmap.main_nav_discover_hl)
                transaction.replace(R.id.fragments,searchFragment)
            }
            R.id.layout_more -> {
                var intent = Intent(this, MoreEditorActivity::class.java)
                startActivity(intent)
            }
            R.id.layout_message -> {
                mDataBinding.imgMessage.setImageResource(R.mipmap.main_nav_message_hl)
                transaction.replace(R.id.fragments,informationFragment)
            }
            R.id.layout_mine -> {
                mDataBinding.imgMine.setImageResource(R.mipmap.main_nav_mine_hl)
                transaction.replace(R.id.fragments,meFragment)
            }
        }
    }

    private fun resetImg(){
        mDataBinding.imgHome.setImageResource(R.mipmap.main_nav_home_normal)
        mDataBinding.imgDiscover.setImageResource(R.mipmap.main_nav_discover_normal)
        mDataBinding.imgMessage.setImageResource(R.mipmap.main_nav_message_normal)
        mDataBinding.imgMine.setImageResource(R.mipmap.main_nav_mine_normal)
    }

}