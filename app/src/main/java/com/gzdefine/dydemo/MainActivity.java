package com.gzdefine.dydemo;

import android.content.Intent;
import android.os.Bundle;

import com.gzdefine.dydemo.View.BaseActivity;
import com.gzdefine.dydemo.View.ImageLoadingActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    @OnClick(R.id.bt1)
    void onClick1(){
        startActivity(new Intent(this, ImageLoadingActivity.class));
    }
    @OnClick(R.id.bt2)
    void onClick2(){
       DYUtil.getInstance().Shock(this,1000);
    }
    @OnClick(R.id.bt3)
    void onClick3(){
//        long[] longs=new long[]{1000l,1000l,2000l,2000l};
//        VibratorUtil.Vibrate(this,longs,true);
    }
}
