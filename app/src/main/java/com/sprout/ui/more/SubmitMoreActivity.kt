package com.sprout.ui.more

import android.content.Intent
import android.content.pm.ShortcutManager
import android.util.SparseArray
import androidx.appcompat.view.SupportMenuInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.base.IItemClick
import com.google.gson.Gson
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.sprout.BR
import com.sprout.R
import com.sprout.app.GlideEngine
import com.sprout.base.BaseActivity
import com.sprout.databinding.ActivitySubmitMoreBinding
import com.sprout.model.ImgData
import com.sprout.ui.more.adapter.SubmitImgAdapter
import com.sprout.vm.BindSubnitMoreActivityViewModel
import kotlinx.android.synthetic.main.activity_more_editor.*
import kotlinx.android.synthetic.main.activity_submit_more.*
import org.json.JSONArray
import org.json.JSONObject

open class SubmitMoreActivity : BaseActivity<BindSubnitMoreActivityViewModel, ActivitySubmitMoreBinding>(
    R.layout.activity_submit_more,
    BindSubnitMoreActivityViewModel::class.java
) {


    var channelId:Int = 0 //频道id
    lateinit var channelName:String
    var themeId:Int = 0 //主题id
    lateinit var themeName:String
    lateinit var address:String  //地址

    var lat:Double = 0.0  //经度
    var lng:Double = 0.0  //纬度
    var type:Int = 1

    var CODE_TAG=100 //主题
    var ADDRESS_TAG=101 //地址
    var imgs: MutableList<ImgData> = mutableListOf()
    var max_img=9
    lateinit var imgAdapter: SubmitImgAdapter

    override fun initView() {
        var layouts = SparseArray<Int>()
        layouts.put(R.layout.layout_submit_imgitem, BR.subitData)
        imgAdapter = SubmitImgAdapter(this,imgs,layouts,ItemClick())
        recyImgs.layoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        recyImgs.adapter = imgAdapter
        imgAdapter.clickEvt = SubmitClickEvt()

        //绑定监听事件
        mDataBinding.submitClick=SubmitClick()

    }


    override fun initVM() {

    }

    override fun initData() {
        var json = intent.getStringExtra("data")
        if(json!!.isNotEmpty()){
            var jsonArr = JSONArray(json)
            for(i in 0 until jsonArr.length()){
                var imgData = Gson().fromJson<ImgData>(jsonArr.getString(i),ImgData::class.java)
                imgs.add(imgData)
            }
            //处理加号
            if(imgs.size < max_img){
                var imgData = ImgData(null, mutableListOf())
                imgs.add(imgData)
            }
        }
    }

    override fun initVariable() {

    }


    /**
     * item条目的点击
     */
    inner class ItemClick: IItemClick<ImgData> {
        override fun itemClick(data: ImgData) {
            //当前点击的是加号
            if(data.path.isNullOrEmpty()){
                openPhoto()
            }
        }
    }

    inner class SubmitClickEvt{
        fun clickDelete(data:ImgData){
            imgs.remove(data)
            imgAdapter.notifyDataSetChanged()
        }
    }


    private fun openPhoto(){
        //当前还能插入的图片数量
        var num = max_img-imgs.size+1
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
            .maxSelectNum(num)
            .imageSpanCount(3)
            .selectionMode(PictureConfig.MULTIPLE)
            .forResult(PictureConfig.CHOOSE_REQUEST)
    }


    /**
     * 打开activity后回传
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PictureConfig.CHOOSE_REQUEST -> {
                // onResult Callback
                val selectList = PictureSelector.obtainMultipleResult(data)
                if (selectList.size == 0) return
                //获取本地图片的选择地址，上传到服务器
                //头像的压缩和二次采样
                //把选中的图片插入到列表
                for(i in 0 until         selectList.size){
                    var imgData = ImgData(selectList.get(i).path, mutableListOf())
                    var index = imgs.size-1
                    imgs.add(index,imgData)
                }
                if(imgs.size > max_img){
                    imgs.removeAt(imgs.lastIndex)
                }
                imgAdapter.notifyDataSetChanged()

            }
        }
        //主题回传值
        if(resultCode==3){
            themeName = data!!.getStringExtra("themeName")!!
            themeId = data!!.getIntExtra("themeId",0)

            txt_theme.text=themeName
            iv_publish_topic.setImageResource(R.mipmap.publish_topic_highlight)

        }
        //主题回传值
        if(resultCode==4){
            val recentlyName = data!!.getStringExtra("recentlyName")
            txt_theme.text=recentlyName
            iv_publish_topic.setImageResource(R.mipmap.publish_topic_highlight)

        }

        //地址
        if(resultCode==5){

            address = data!!.getStringExtra("address")!!
            lat = data!!.getDoubleExtra("lat",0.0)
            lng = data!!.getDoubleExtra("lng",0.0)
            txt_address.setText(address)

        }

    }


    /**
     * 组装提交的内容
     */
    fun getSubmitJson():String{
        var title = edit_title.toString()
        var mood =edit_mood.toString()
        var json: JSONObject = JSONObject()
        json.put("type",type)
        json.put("title",title)
        json.put("mood",mood)
        json.put("address",address)
        json.put("themeid",themeId)
        json.put("channelid",channelId)
        json.put("lng",lng)
        json.put("lat",lat)
        var res = JSONArray()
        when(type){
            1 -> { //图片
                for(i in 0 until imgs.size){
                    var img = JSONObject()
                    img.put("url",imgs[i].path)
                    var tags = JSONArray()
                    img.put("tags",tags)
                    for(j in 0 until imgs[i].tags.size){
                        var tagItem = imgs[i].tags[j]
                        var tag = JSONObject()
                        tag.put("type",tagItem.type)
                        tag.put("id",tagItem.id)
                        tag.put("name",tagItem.name)
                        tag.put("x",tagItem.x)
                        tag.put("y",tagItem.y)
                        tag.put("lng",tagItem.lng)
                        tag.put("lat",tagItem.lat)
                        tags.put(tag)
                    }
                    json.put("res",res)
                }
            }
            2 -> {

            }
        }

        return json.toString()
    }

    //监听事件
    inner class SubmitClick{
        fun click(type:Int){
            when(type){
                1->{
                    /**
                     * 跳转到获取数据页面
                     */
                    intentTheme()
                }
                2->{
                    /**
                     * 跳转到获取位置页面
                     */
                    intentAddress()

                }
                3->{

                }
                4->{

                }
                5->{

                }
                //发布
                6->{
                    var content = getSubmitJson()
                    mViewModel.submitThrends(content)

                }
            }
        }
    }


    //主题
    private fun intentTheme(){
        //跳转
        val intent = Intent(this, ThemeActivity::class.java)
        startActivityForResult(intent, CODE_TAG)

    }

    //位置
    private fun intentAddress(){
        //跳转
        val intent = Intent(this, AddressActivity::class.java)
        startActivityForResult(intent, ADDRESS_TAG)

    }


}