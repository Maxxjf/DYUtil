package com.gzdefine.dydemo;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author Max
 * TODO：
 * Created by Max on 2017/9/6.
 */

public class DYUtil {
    private static volatile DYUtil instance=null;
    /**
     *单列模式
     *
     */
    public static DYUtil getInstance(){
        synchronized(DYUtil.class){
            if(instance==null){
                instance=new DYUtil();
            }
        }
        return instance;
    }
    private DYUtil(){}
    /**
     * Glide.with(this)
     .load(url)
     .placeholder(R.drawable.loading)              加载中的图片
     .error(R.drawable.error)                      加载错误的图片
     .diskCacheStrategy(DiskCacheStrategy.NONE)    禁止使用缓存机制
     .into(imageView);
     * 单列模式
     *@param activity 图层act
     * @param imageView ImageView控件
     * @param object    uri： 统一标识符
     *                  String：网络图片url，或者uri
     *                  File：指定文件
     *                  byte[]:二进制流
     */
    public void loadImage(Activity activity, Object object, ImageView imageView){
        Glide.with(activity).load(object).placeholder(R.mipmap.loading).error(R.mipmap.error).into(imageView);
    }


}
