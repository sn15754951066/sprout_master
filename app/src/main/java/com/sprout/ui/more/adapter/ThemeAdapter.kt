package com.sprout.ui.more.adapter

import android.content.Context
import android.util.SparseArray
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.example.myapplication.base.BaseAdapter
import com.example.myapplication.base.IItemClick
import com.sprout.BR
import com.sprout.R
import com.sprout.model.ThemeData
import com.sprout.ui.more.ThemeActivity

class ThemeAdapter(
    context: Context,
    list: MutableList<ThemeData>,
    layouts: SparseArray<Int>,
    click: IItemClick<ThemeData>
) : BaseAdapter<ThemeData>(context, list, layouts, click) {
    override fun layoutId(position: Int): Int {
        return R.layout.adapter_theme
    }

    override fun bindData(binding: ViewDataBinding, data: ThemeData, layoutId: Int) {

        binding.setVariable(BR.themeData ,data)


    }
}