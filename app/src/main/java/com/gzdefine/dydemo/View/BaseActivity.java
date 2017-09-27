package com.gzdefine.dydemo.View;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.gzdefine.dydemo.R;
import com.gzdefine.dydemo.Util.NetUtils;
import com.gzdefine.dydemo.Util.SPUtils;
import com.gzdefine.dydemo.Util.SystemBarTintManager;

import java.util.ArrayList;

/**
 * 所有Activity都要继承的BaseActivity
 * TODO：
 * Created by Max on 2017/9/5.
 */

public class BaseActivity extends AppCompatActivity {

    TextView title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.windowBackground);//通知栏所需颜色
        }
        getinfo();
    }

    public void getinfo() {
        String localClassName = this.getLocalClassName();
        Log.d("max", "localClassName:" + localClassName);
        ArrayList<UserHabitCollection> users= (ArrayList<UserHabitCollection>) SPUtils.get(this,"UserHabitCollection",new ArrayList<>());
        Log.d("max","users:"+users);
        UserHabitCollection userHabitCollection=new UserHabitCollection();
        userHabitCollection.setActName(localClassName);
        if (users==null){
            users=new ArrayList<>();
        }
        users.add(userHabitCollection);
        if (NetUtils.isWifi(this)){
            //TODO WIFI情况下发送用户习惯
//            users.clear();//清空
        }
        Log.d("max","list长度："+users.size());
        SPUtils.put(this,"UserHabitCollection",users);
        ArrayList<UserHabitCollection> users2= (ArrayList<UserHabitCollection>) SPUtils.get(this,"UserHabitCollection",new ArrayList<>());
        Log.d("max","users:"+users2);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void setFinishOnTouchOutside(boolean finish) {
        super.setFinishOnTouchOutside(finish);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        Log.d("max", "maax:");
//        Log.d("max", "getActionIndex:" + event.getActionIndex());
//        Log.d("max", "getButtonState:" + event.getButtonState());
//        Log.d("max", "getDeviceId:" + event.getDeviceId());
//        Log.d("max", "getDeviceId:" + event.getDeviceId());
//        return false;
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
