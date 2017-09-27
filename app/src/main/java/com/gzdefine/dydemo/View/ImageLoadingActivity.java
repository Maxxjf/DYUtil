package com.gzdefine.dydemo.View;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.gzdefine.dydemo.DYUtil;
import com.gzdefine.dydemo.R;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageLoadingActivity extends BaseActivity {

    @Bind(R.id.iv1)
    ImageView iv1;
    @Bind(R.id.iv2)
    ImageView iv2;
    @Bind(R.id.iv3)
    ImageView iv3;
    @Bind(R.id.iv4)
    ImageView iv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loading);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        // 加载本地图片
        File file = new File(getExternalCacheDir() + "/image.jpg");
        DYUtil.getInstance().loadImage(this,file,iv1);

// 加载应用资源
        int resource = R.mipmap.ic_launcher;
        DYUtil.getInstance().loadImage(this,R.mipmap.ic_launcher,iv2);

// 加载二进制流
        byte[] image = getImageBytes();
        DYUtil.getInstance().loadImage(this,image,iv3);

// 加载Uri对象
        String imageUrl ="http://img.jiqie.com/z/0/5/1034mz.jpg";
        DYUtil.getInstance().loadImage(this,imageUrl,iv4);
    }


    // ------------------------将drawable 图像转化成二进制字节----------------
    public  synchronized  byte[] getImageBytes() {
        Resources resources = this.getResources();
        Drawable drawable = resources.getDrawable(R.mipmap.ic_launcher);
        if (drawable != null) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            int size = bitmap.getWidth() * bitmap.getHeight() * 4;
            // 创建一个字节数组输出流,流的大小为size
            ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
            // 设置位图的压缩格式，质量为100%，并放入字节数组输出流中
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            // 将字节数组输出流转化为字节数组byte[]
            byte[] imagedata = baos.toByteArray();
            return imagedata;
        }
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
