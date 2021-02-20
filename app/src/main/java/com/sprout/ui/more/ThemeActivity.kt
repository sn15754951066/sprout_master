package com.sprout.ui.more

import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.base.IItemClick
import com.sprout.BR
import com.sprout.R
import com.sprout.base.BaseActivity
import com.sprout.databinding.ActivityThemeBinding
import com.sprout.model.ThemeData
import com.sprout.ui.more.adapter.ThemeAdapter
import com.sprout.ui.more.adapter.RecentlyAdapter
import com.sprout.vm.BindThemeActivityViewModel
import kotlinx.android.synthetic.main.activity_theme.*

class ThemeActivity : BaseActivity<BindThemeActivityViewModel, ActivityThemeBinding>(
    R.layout.activity_theme,
    BindThemeActivityViewModel::class.java
) {

    //集合
    //适配器
    lateinit var themeList: MutableList<ThemeData>
    lateinit var themeAdapter: ThemeAdapter

    lateinit var recentlyList: MutableList<String>
    lateinit var recentlyAdapter: RecentlyAdapter


    override fun initView() {

        rv_recently.layoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)

        var recentlyArr = SparseArray<Int>()
        recentlyArr.put(R.layout.adapter_recently,BR.recentlyData)
        recentlyList= mutableListOf()
        recentlyAdapter= RecentlyAdapter(this,recentlyList,recentlyArr,RecentlyClick())



        rv_them.layoutManager = LinearLayoutManager(this)

        var themeArr = SparseArray<Int>()
        themeArr.put(R.layout.adapter_theme, BR.themeData)
        themeList = mutableListOf()
        themeAdapter = ThemeAdapter(this, themeList, themeArr, ThemeClick())

        //rv_them!!.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        cl_not_join.setOnClickListener {
            themeList.clear()

            //themeAdapter.notifyDataSetChanged()
            finish()
        }

    }


    override fun initVM() {

        mViewModel.themeData.observe(this, Observer {
            themeList.clear()
            themeList!!.addAll(it)
            rv_them!!.adapter = themeAdapter
            themeAdapter.notifyDataSetChanged()

        })

    }


    override fun initData() {

        if (themeList.size == 0) {
            mViewModel.getTheme()
        } else {
            rv_them!!.adapter = themeAdapter
        }

        if(recentlyList.size!=null){
            rv_recently!!.adapter=recentlyAdapter
        }

    }

    override fun initVariable() {

    }

    inner class ThemeClick : IItemClick<ThemeData> {
        override fun itemClick(data: ThemeData) {

            if(themeList!=null){
                intent.putExtra("themeName", data.name)
                intent.putExtra("themeId",data.id)
                setResult(3, intent)
                //放到集合里
                recentlyList.add(data.name)


            }

            finish()
        }

    }

    inner class RecentlyClick : IItemClick<String> {
        override fun itemClick(data: String) {

            intent.putExtra("recentlyName", data)
            setResult(4, intent)

            finish()
        }

    }
}