package com.sprout.ui.more

import android.os.Bundle
import android.util.SparseArray
import android.widget.BaseAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.base.IItemClick
import com.sprout.BR
import com.sprout.R
import com.sprout.base.BaseActivity
import com.sprout.databinding.ActivityTagsBinding
import com.sprout.model.BrandData
import com.sprout.model.GoodsData
import com.sprout.ui.more.adapter.BrandAdapter
import com.sprout.ui.more.adapter.GoodsAdapter
import com.sprout.vm.BindTagsActivityViewModel
import kotlinx.android.synthetic.main.activity_tags.*

class TagsActivity : BaseActivity<BindTagsActivityViewModel, ActivityTagsBinding>(
    R.layout.activity_tags,
    BindTagsActivityViewModel::class.java
) {

    lateinit var brandList:MutableList<BrandData.DataX>
    lateinit var brandAdapter:BrandAdapter

    lateinit var goodList:MutableList<GoodsData.DataX>
    lateinit var goodAdapter: GoodsAdapter


    override fun initData() {

    }

    override fun initVM() {
        mViewModel.brandList.observe(this, Observer {
            brandList.clear()
            brandList.addAll(it.data)
            recyTags!!.adapter = brandAdapter
        })

        mViewModel.goodsList.observe(this, Observer {
            goodList.clear()
            goodList.addAll(it.data)
            recyTags.adapter = goodAdapter
        })
    }

    override fun initVariable() {

    }

    override fun initView() {
        recyTags.layoutManager = LinearLayoutManager(this)

        var brandArr = SparseArray<Int>()
        brandArr.put(R.layout.layout_brand_item,BR.brandData)
        brandList = mutableListOf()
        brandAdapter = BrandAdapter(this,brandList,brandArr,BrandClick())

        var goodArr = SparseArray<Int>()
        goodArr.put(R.layout.layout_good_item,BR.goodsData)
        goodList = mutableListOf()
        goodAdapter = GoodsAdapter(this,goodList,goodArr,GoodClick())

        mDataBinding.tagClick = TagsClick()
    }


    inner class BrandClick:IItemClick<BrandData.DataX>{
        override fun itemClick(data: BrandData.DataX) {
            intent.putExtra("name",data.name)
            intent.putExtra("id",data.id)
            setResult(1,intent)
            finish()
        }

    }

    inner class GoodClick:IItemClick<GoodsData.DataX>{
        override fun itemClick(data: GoodsData.DataX) {
            intent.putExtra("name",data.name)
            intent.putExtra("id",data.id)
            setResult(2,intent)
            finish()
        }

    }

    inner class TagsClick{
        fun click(type:Int){
            when(type){
                1->{
                    if(brandList.size == 0){
                        mViewModel.getBrand()
                    }else{
                        recyTags.adapter = brandAdapter
                    }
                }
                2->{
                    if(goodList.size == 0){
                        mViewModel.getGoods()
                       }else{
                        recyTags.adapter = goodAdapter
                    }
                }
                3->{

                }
                4->{

                }
            }
        }
    }


}