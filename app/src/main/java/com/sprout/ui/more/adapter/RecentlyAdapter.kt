package com.sprout.ui.more.adapter

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.myapplication.base.BaseAdapter
import com.example.myapplication.base.IItemClick
import com.sprout.BR
import com.sprout.R

class RecentlyAdapter(
    context: Context,
    list: MutableList<String>,
    layouts: SparseArray<Int>,
    click: IItemClick<String>
) : BaseAdapter<String>(context, list, layouts, click) {
    override fun layoutId(position: Int): Int {
        return R.layout.adapter_recently
    }

    override fun bindData(binding: ViewDataBinding, data: String, layoutId: Int) {
        binding.setVariable(BR.recentlyData,data)

    }

}
