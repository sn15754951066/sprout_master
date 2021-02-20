package com.sprout.ui.more.adapter

import android.content.Context
import android.net.Uri
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.example.myapplication.base.BaseAdapter
import com.example.myapplication.base.IItemClick
import com.shop.ext.findView
import com.sprout.BR
import com.sprout.R
import com.sprout.model.ImgData
import com.sprout.ui.more.SubmitMoreActivity

class SubmitImgAdapter(
    context: Context,
    list: MutableList<ImgData>,
    layouts: SparseArray<Int>,
    click: IItemClick<ImgData>
) :
    BaseAdapter<ImgData>(context, list, layouts, click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_submit_imgitem
    }

    lateinit var clickEvt: SubmitMoreActivity.SubmitClickEvt

    override fun bindData(binding: ViewDataBinding, data: ImgData, layoutId: Int) {
        binding.setVariable(BR.submitImgClick, itemClick)
        var img = binding.root.findViewById<ImageView>(R.id.img)
        var imgDelete = binding.root.findViewById<ImageView>(R.id.img_delete)
        if (data.path.isNullOrEmpty()) {
            img.setImageResource(R.mipmap.ic_addimg)
            imgDelete.visibility = View.GONE
        } else {
            img.setImageURI(Uri.parse(data.path))
        }
        imgDelete.tag = data
        img.setOnClickListener {

            if (data.path.isNullOrEmpty()) {
                return@setOnClickListener
            }

            imgDelete.visibility =
                if (imgDelete.visibility == View.GONE) View.VISIBLE else View.GONE

        }
        imgDelete.setOnClickListener {
            if (clickEvt != null) {
                imgDelete.visibility = View.GONE
                clickEvt.clickDelete(imgDelete.tag as ImgData)
            }
        }

    }
}