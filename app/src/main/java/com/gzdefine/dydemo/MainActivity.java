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
}
