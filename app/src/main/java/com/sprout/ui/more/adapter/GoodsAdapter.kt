package com.sprout.ui.more.adapter

import android.content.Context
import android.util.SparseArray
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.example.myapplication.base.BaseAdapter
import com.example.myapplication.base.IItemClick
import com.sprout.BR
import com.sprout.R
import com.sprout.model.GoodsData

/**
 * 商品的adapter
 */
class GoodsAdapter(
    context: Context,
    list: List<GoodsData.DataX>,
    layouts: SparseArray<Int>,
    click: IItemClick<GoodsData.DataX>
) : BaseAdapter<GoodsData.DataX>(context, list, layouts, click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_good_item
    }

    override fun bindData(binding: ViewDataBinding, data: GoodsData.DataX, layId: Int) {

        binding.setVariable(BR.goodsItemClick, itemClick)
        var txt = binding.root.findViewById<TextView>(R.id.tv_goods_text)
        txt.setText(data.name)
    }


}